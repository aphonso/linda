package be.lindacare.currency.market.aphonso.config;

import java.net.InetAddress;


import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 
 * @author aphonso
 * 
 * Class that configures Elastic Search
 *
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "be.lindacare.currency.market.aphonso.repository")
public class ElasticConfig {

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    @Bean
    public String indexName() {
        return this.indexName;
    }
	
	@Bean
	@SuppressWarnings("resource")
    public Client client() throws Exception {

        Settings settings = Settings.builder()
                .put("cluster.name", this.clusterName)
                .build();

        return new PreBuiltTransportClient(settings)
        		.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(this.host), this.port));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(this.client());
    }
}
