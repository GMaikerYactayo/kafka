package com.mg.order_service.config;

public class KafkaTopicConfig {
/**
    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete (borra mensaje) - compact (Mantiene el mas actual)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // Tiempo de retencion de mensajes, defecto -1
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamanio maximo del segmento - defecto 1073741824 bytes - 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // Tamanio maximo de cada mensaje - defecto 1000000 - 1 MB

        return TopicBuilder.name("order-topic")
                .partitions(2) // number of partitions
                .replicas(2) // number of replicas
                .configs(configurations)
                .build();
    }**/

}
