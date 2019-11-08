package br.com.mhbp.threads.parallel.phaser;

import java.util.concurrent.Phaser;

public class Task {
    private Phaser ph;

    public Task(Phaser ph) {
        this.ph = ph;
    }
}
