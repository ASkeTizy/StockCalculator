package org.example.stockcalc.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OnSpecificHostCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            try {
                String currentHostName = InetAddress.getLocalHost().getHostName();
                // Получаем ожидаемое имя из аннотации или настроек
                String targetHost = "IT-307";
                return targetHost.equalsIgnoreCase(currentHostName);
            } catch (UnknownHostException e) {
                return false;
            }
        }


}

