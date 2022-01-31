import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestMedicalServiceImpl {

    @Test
    void testWarningMessageBloodPressureAndTemperature(){
        PatientInfo patientInfoTest =  new PatientInfo("Семен", "Михайлов", LocalDate.of(1982, 1, 16),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78)));

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(patientInfoTest);

        SendAlertService alertService = Mockito.mock(SendAlertService.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        medicalService.checkBloodPressure("2563", new BloodPressure(140,80));
        medicalService.checkTemperature("2563", new BigDecimal("38.6"));

        Mockito.verify(alertService, Mockito.times(2)).send(Mockito.anyString());

    }

    @Test
    void testNormalBloodPressureAndTemperature(){
        PatientInfo patientInfoTest =  new PatientInfo("Семен", "Михайлов", LocalDate.of(1982, 1, 16),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78)));

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(patientInfoTest);

        SendAlertService alertService = Mockito.mock(SendAlertService.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        medicalService.checkBloodPressure("2563", new BloodPressure(125,78));
        medicalService.checkTemperature("2563", new BigDecimal("35.3"));

        Mockito.verify(alertService, Mockito.times(0)).send(Mockito.anyString());


    }
}
