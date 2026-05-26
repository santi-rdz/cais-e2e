import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Pages.EmergenciesPage;

public class EmergenciesTest extends BaseTest {
    EmergenciesPage emergenciesPage;
    @BeforeMethod
    public void navigateToEmergency() {
        emergenciesPage = new EmergenciesPage();
        emergenciesPage.open(); // driver.get("http://localhost:5173/emergencias")
    }

    @Test(description = "Añadir emergencia a la bitácora")
    public void addEmergency(){
        emergenciesPage.addEmergency("Cafeteria","Jose","2207277","1234567890","Pierna rota","Nada");
        Assert.assertEquals(emergenciesPage.getToastTitle(), "Emergencia creada exitosamente");
    }

    @Test(description = "Visualizar emergencia de la bitácora")
    public void viewEmergency(){
        emergenciesPage.viewEmergency();
        String url = emergenciesPage.getCurrentUrl();
        String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        String id = url.substring(url.lastIndexOf('/') + 1);
        Assert.assertTrue(id.matches(uuidRegex));
    }

    @Test(description = "Eliminar una emergencia de la bitácora")
    public void deleteEmergency(){
        String date = emergenciesPage.getDate();
        String toast = "Emergencia del " + date + " eliminada";
        emergenciesPage.eraseEmergency();
        Assert.assertEquals(emergenciesPage.getToastTitle(), toast);

    }
}
