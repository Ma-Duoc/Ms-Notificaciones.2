package com.medictime.notificaciones.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificacionListener {

    private static final Logger log = LoggerFactory.getLogger(NotificacionListener.class);

    @RabbitListener(queues = "cola.notificacion.correo")
    public void procesarCorreo(Map<String, Object> evento) {
        log.info("------------------------------------------------------------");
        log.info("📧 [WORKER CORREO] Procesando envío de email...");
        log.info("📩 Para médico: {}", evento.get("nombreCompleto"));
        log.info("👤 Teléfono contacto: {}", evento.get("telefono"));
        log.info("🔑 Clave Provisoria: {}", evento.get("passwordProvisoria"));
        log.info("📄 Especialidad: {}", evento.get("especialidad"));
        log.info("✅ [CORREO] Notificación procesada exitosamente.");
        log.info("------------------------------------------------------------");
    }

    @RabbitListener(queues = "cola.notificacion.sms")
    public void procesarSms(Map<String, Object> evento) {
        log.info("------------------------------------------------------------");
        log.info("📱 [WORKER SMS] Procesando envío de SMS...");
        log.info("📞 Enviando a: {}", evento.get("telefono"));
        log.info("💬 Mensaje: Hola {}, tu clave temporal es: {}", 
                 evento.get("nombreCompleto"), evento.get("passwordProvisoria"));
        log.info("✅ [SMS] Mensaje entregado con éxito.");
        log.info("------------------------------------------------------------");
    }
}
