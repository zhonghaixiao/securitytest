package com.example.securitytest.jdktest;

import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.Channel;

import java.util.concurrent.Executor;

public class DirectExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
