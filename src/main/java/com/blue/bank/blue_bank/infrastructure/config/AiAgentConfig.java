package com.blue.bank.blue_bank.infrastructure.config;
import com.blue.bank.blue_bank.infrastructure.adapter.in.ai.BlueBankTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AiAgentConfig {
    @Bean
    public ChatClient chatClient(
        ChatClient.Builder builder,
        BlueBankTools blueBankTools
        )
    {
        return builder
                .defaultSystem(
                "Sos un agente bancario del banco Blue Bank. "
                        + "Ayudas a los clientes a operar con sus cuentas. "
                        + "Respondé siempre en español"
        ).defaultTools(blueBankTools).build();
    }
}