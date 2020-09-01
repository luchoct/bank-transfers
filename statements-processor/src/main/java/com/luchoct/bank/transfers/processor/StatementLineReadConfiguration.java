package com.luchoct.bank.transfers.processor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.messaging.MessageChannel;

import java.nio.charset.Charset;

/**
 * All configuration related to statementLineRead channel.
 * This channel receives a message once a statements line is read from file.
 */
@Configuration
public class StatementLineReadConfiguration {
    @Splitter(inputChannel = "statementsFileRead")
    @Bean
    public FileSplitter fileSplitter(
            @Value("${sourceFiles.charset}") final String charset,
            @Qualifier("statementLineRead") final MessageChannel outputChannel) {
        final FileSplitter fileSplitter = new FileSplitter(true);
        fileSplitter.setCharset(Charset.forName(charset));
        fileSplitter.setOutputChannel(outputChannel);
        return fileSplitter;
    }

    @Bean
    public MessageChannel statementLineRead() {
        return new PublishSubscribeChannel();
    }
}
