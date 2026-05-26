import Base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Pages.EditPage;
import com.Pages.PacientesPage;

public class PacientesTest extends BaseTest {

    private PacientesPage pacientesPage;
    String testName = "Juan";
    String testLastName = "Perez";
    String fullName = testName + " " + testLastName;
    String testBirthDay = "10/05/2001";
    String testTelephoneNumber = "6648995013";
    String testEditedTelephoneNumber = "6648998888";
    String testFakeName = "Rafaelino";

    @BeforeMethod
    public void setUp() {
        pacientesPage = new PacientesPage();
        pacientesPage.openFromSidebar();
        Assert.assertTrue(pacientesPage.getCurrentUrl().contains("/pacientes"));
    }

    @Test(description = "register new patient to Pacientes")
    public void registerNewPatient() {
        pacientesPage.addNewPatient();
        Assert.assertEquals(pacientesPage.getModalTitle(), "Registro de Nuevo Paciente");
        pacientesPage.filloutNewPatient(testName, testLastName, testBirthDay, testTelephoneNumber);
        Assert.assertTrue(pacientesPage.isNewPatientOnPatientList(fullName));
    }

    @Test(description = "edit patient data")
    public void editPatientData() {
        pacientesPage.openFirstPatientByName(fullName);

        EditPage editPage = new EditPage();
        Assert.assertEquals(editPage.getPatientNameInfo(), fullName);
        editPage.editPatient();
        Assert.assertEquals(editPage.getModalTitle(), "Editar Info del Paciente");
        editPage.editInfo(testEditedTelephoneNumber, "juanperez@mail.com");
        Assert.assertEquals(editPage.getInfoTelephoneNumber(), testEditedTelephoneNumber);
    }

    @Test(description = "search for a specific patient")
    public void searchForPatient() {
        pacientesPage.searchForPatient(testName);
        Assert.assertTrue(pacientesPage.isNewPatientOnPatientList(fullName));
    }

    @Test(description = "search for a non-existent patient")
    public void searchForNonExistentPatient() {
        pacientesPage.searchForPatient(testFakeName);
        Assert.assertTrue(pacientesPage.isNoDataVisible());
    }

    @Test(description = "sort patient list by age (oldest to youngest)")
    public void sortPatientListOldestToYoungest() {
        pacientesPage.selectSortByOldestToYoungest();
        Assert.assertTrue(pacientesPage.isSortedOldestToYoungest());
    }

    @Test(description = "filter patient list to show only masculine patients")
    public void sortPatientListYoungestToOldest() {
        pacientesPage.seletcFilterByMasculine();
        Assert.assertTrue(pacientesPage.isShownOnlyMasculine());
    }
}