package com.luchoct.bank.transfers.processor;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.filters.FtpSimplePatternFileListFilter;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizer;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizingMessageSource;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

import java.io.File;

/**
 * All configuration related to statementsFileRead channel.
 * This channel receives a message once a statements file is read from FTP server.
 */
@Configuration
public class StatementsFileReadConfiguration {
    @Bean
    public SessionFactory<FTPFile> sftpSessionFactory(
            @Value("${ftpServer.host}") final String host,
            @Value("${ftpServer.port}") final int port,
            @Value("${ftpServer.username}") final String username,
            @Value("${ftpServer.password}") final String password,
            @Value("${ftpServer.clientMode}") final int clientMode) {
        final DefaultFtpSessionFactory factory = new DefaultFtpSessionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        // 0=ACTIVE, 2=PASIVE
        factory.setClientMode(clientMode);
        return new CachingSessionFactory<>(factory);
    }

    @Bean
    public FtpInboundFileSynchronizer ftpInboundFileSynchronizer(final SessionFactory<FTPFile> sessionFactory) {
        final FtpInboundFileSynchronizer fileSynchronizer = new FtpInboundFileSynchronizer(sessionFactory);
        //fileSynchronizer.setDeleteRemoteFiles(false);
        fileSynchronizer.setFilter(new FtpSimplePatternFileListFilter("*.csv"));
        return fileSynchronizer;
    }

    //TODO check poller synchronization factory.
    @Bean
    @InboundChannelAdapter(channel = "statementsFileRead", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<File> statementsFileReadSource(
            final FtpInboundFileSynchronizer fileSynchronizer,
            @Value("${channel.statementsFileRead.localDirectory}") final String localDirectory) {
        final FtpInboundFileSynchronizingMessageSource source =
                new FtpInboundFileSynchronizingMessageSource(fileSynchronizer);
        source.setLocalDirectory(new File(localDirectory));
        source.setAutoCreateLocalDirectory(true);
        source.setLocalFilter(new AcceptOnceFileListFilter<>());
        return source;
    }
}
