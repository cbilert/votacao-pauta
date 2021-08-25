package br.com.votacaopauta.service;

import br.com.votacaopauta.entity.Pauta;
import br.com.votacaopauta.entity.SessaoVotacao;
import br.com.votacaopauta.repository.SessaoVotacaoRepository;
import br.com.votacaopauta.repository.VotoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

@ApplicationScoped
public class SessaoVotacaoService {

    @Inject    SessaoVotacaoRepository sessaoVotacaoRepository;
    @Inject    VotoRepository votoRepository;
    @Inject    UserTransaction userTransaction;

    public void init(Pauta pauta, Long tempoValidadeSessao) {
        SessaoVotacao sessaoVotacao = abrirSessao(pauta);
        aguardarEncerramento(tempoValidadeSessao,sessaoVotacao);
    }

    private SessaoVotacao abrirSessao(Pauta pauta) {
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setAbertura(LocalDateTime.now());
        sessaoVotacaoRepository.persist(sessaoVotacao);
        return sessaoVotacao;
    }

    @Transactional
    private void aguardarEncerramento(Long tempoEmMilissegundos, SessaoVotacao sessaoVotacao) {
        if (tempoEmMilissegundos == null || tempoEmMilissegundos == 0L) {
            tempoEmMilissegundos = 60000L; //1 minuto por default
        }
        Timer timer = new Timer(tempoEmMilissegundos);
        TimerRunner timerRunner = new TimerRunner(timer, userTransaction, sessaoVotacaoRepository,sessaoVotacao, votoRepository);
        timerRunner.start();
    }
}

class Timer extends Thread {

    Long time;

    public Timer(Long time) {
        this.time = time;
    }

    @Override
    public void run() {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TimerRunner extends Thread {
    Thread thread;
    SessaoVotacaoRepository sessaoVotacaoRepository;
    SessaoVotacao sessaoVotacao;
    VotoRepository votoRepository;
    UserTransaction userTransaction;
    public TimerRunner(Thread thread, UserTransaction userTransaction, SessaoVotacaoRepository sessaoVotacaoRepository, SessaoVotacao sessaoVotacao, VotoRepository votoRepository) {
        this.thread = thread;
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.sessaoVotacao = sessaoVotacao;
        this.userTransaction = userTransaction;
        this.votoRepository = votoRepository;
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(500);
                if(!thread.isAlive()) {
                    fecharSessao(sessaoVotacao);
                    System.out.println("Sessao Encerada...");
                    break;
                }
                System.out.println("Waiting...");
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fecharSessao(SessaoVotacao sessaoVotacao) {
        try {
            userTransaction.begin();
            sessaoVotacao = sessaoVotacaoRepository.findById(sessaoVotacao.getId());
            sessaoVotacao.setEncerramento(LocalDateTime.now());
            sessaoVotacao.setVotosSim(votoRepository.countVotosPositivos(sessaoVotacao.getPauta().getId()));
            sessaoVotacao.setVotosNao(votoRepository.countVotosNegativos(sessaoVotacao.getPauta().getId()));
            sessaoVotacaoRepository.persist(sessaoVotacao);
            userTransaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
