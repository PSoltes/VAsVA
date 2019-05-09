package vava.soltesvasko.lezeckastena.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest interface na logovanie z aplikácie.
@RestController
public class LoggingController {

    final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    // Sem prídu všetky loggy, je dostupných nasledovných 5 úrovní logovania.
    @RequestMapping("/log/{severity}/{message}")
    public String index(@PathVariable String message, @PathVariable String severity) {
        switch(severity) {
            case "TRACE":
                logger.trace(message);
                break;
            case "DEBUG":
                logger.debug(message);
                break;
            case "INFO":
                logger.info(message);
                break;
            case "WARN":
                logger.warn(message);
                break;
            case "ERROR":
                logger.error(message);
                break;
        }

        return "Logged";
    }
}