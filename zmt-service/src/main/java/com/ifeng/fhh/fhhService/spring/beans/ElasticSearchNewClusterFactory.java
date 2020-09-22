package com.ifeng.fhh.fhhService.spring.beans;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 19-4-22 下午8:15
 */
@Configuration
public class ElasticSearchNewClusterFactory {

    @Value("${searchEngine.elasticsearch.cluster.hosts}")
    private String clusterHosts;

    @Bean
    public TransportClient transportClient() {

        Settings.Builder builder = Settings.builder();
        builder.put("cluster.name", "fhh");
        builder.put(TransportClient.CLIENT_TRANSPORT_SNIFF.getKey(), true);
        builder.put(TransportClient.CLIENT_TRANSPORT_PING_TIMEOUT.getKey(), 5, TimeUnit.SECONDS);
        builder.put(TransportClient.CLIENT_TRANSPORT_NODES_SAMPLER_INTERVAL.getKey(), 5, TimeUnit.SECONDS);

        TransportClient client = new PreBuiltTransportClient(
                builder.build()
        );

        try {
            List<InetSocketTransportAddress> transportAddressList = parseHosts(clusterHosts);
            if (transportAddressList == null
                    || transportAddressList.size() == 0) {
                throw new RuntimeException("no available node");
            }
            transportAddressList.forEach(client::addTransportAddress);
        } catch (UnknownHostException e) {
            throw new RuntimeException("cluster hosts illegal", e);
        }


        return client;

    }

    public static List<InetSocketTransportAddress> parseHosts(String clusterHosts) throws UnknownHostException {
        Objects.requireNonNull(clusterHosts);
        String[] hosts = clusterHosts.split(",");
        List<InetSocketTransportAddress> transportAddressList = new ArrayList<>();
        for (String host : hosts) {
            if (Objects.equals(host, "")) {
                continue;
            }
            String[] meta = host.split(":");
            InetAddress address = InetAddress.getByName(meta[0]);
            int port = Integer.valueOf(meta[1]);
            transportAddressList.add(
                    new InetSocketTransportAddress(
                            address,
                            port
                    )
            );
        }
        return transportAddressList;
    }
}
