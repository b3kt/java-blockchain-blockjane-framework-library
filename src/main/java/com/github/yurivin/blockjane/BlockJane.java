package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.infrastracture.Chaining;
import com.github.yurivin.blockjane.infrastracture.Environment;
import com.github.yurivin.blockjane.wallet.iWallet;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.security.PublicKey;

@Data
@Slf4j
public class BlockJane {

    private boolean running;
    private final Environment env;

    public BlockJane(Environment env) {
        if(env == null) {
            throw new IllegalStateException("BlockJane environment should be not null");
        }
        this.env = env;
    }

    public void run() {
        running = true;
        Thread chaining = new Thread(new Chaining(this));
        chaining.start();
    }

    public void addBlockData(String data) {
        env.blockchain.addBlockData(data);
    }

    public iWallet getWallet(PublicKey publicKey) {
        return env.wallets.get(publicKey);
    }

    public iWallet createWallet() {
        try {
            return (iWallet)env.walletType.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating wallet", e);
        }
    }

}
