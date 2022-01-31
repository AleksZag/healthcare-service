import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;

public class TestSendAlertServiceImpl {

    @Test
    void testSendMessage(){
        SendAlertService sendMessage = Mockito.spy(SendAlertServiceImpl.class);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        sendMessage.send("test message");

        Mockito.verify(sendMessage).send(argumentCaptor.capture());

        Assertions.assertEquals("test message", argumentCaptor.getValue());
    }
}
