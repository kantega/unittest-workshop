package no.kantega.unittesting.exercises.confirmation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ConfirmationServiceTest {

    private ConfirmationService confirmationService;
    private RoleRepository roleRepository;
    private ConfirmationRepository confirmationRepository;

    private Document document;
    private Role pilot;
    private Role host;
    private DistributionGroup onBoardPersonnel;
    private Employee aPilot;
    private Employee aHost;
    private List<Employee> employeesInDistributionGroup;


    @Before
    public void setup() {

        roleRepository = mock(RoleRepository.class);
        confirmationRepository = mock(ConfirmationRepository.class);
        confirmationService = new ConfirmationService(roleRepository, confirmationRepository);

        document = new Document(1L);
        aPilot = new Employee(1L);
        aHost = new Employee(2L);
        pilot = new Role("PILOT");
        host = new Role("HOST");
        onBoardPersonnel = new DistributionGroup();

    }

    @Test
    public void shallPersistNewConfirmations() {

        givenDocumentToBeDistributedToEmployees();

        whenRequiringConfirmationForDocument();

        thenConfirmationsArePersisted();

    }

    @Test
    public void shallCreatePendingConfirmations() {

        givenDocumentToBeDistributedToEmployees();

        whenRequiringConfirmationForDocument();

        thenConfirmationsArePending();

    }

    @Test
    public void shallCreateConfirmationsForDocument() {

        givenDocumentToBeDistributedToEmployees();

        whenRequiringConfirmationForDocument();

        thenConfirmationsApplyToDistributedDocument();

    }

    @Test
    public void shallCreateConfirmationsForAllEmployeesInDistributionGroup() {

        givenDocumentToBeDistributedToEmployees();

        whenRequiringConfirmationForDocument();

        thenConfirmationsApplyToAllEmployeesInDistributionGroup();

    }

    private void givenDocumentToBeDistributedToEmployees() {

        document.setDistributionGroup(onBoardPersonnel);
        employeesInDistributionGroup = Arrays.asList(aPilot, aHost);

        given(roleRepository.findRolesForDistributionGroup(onBoardPersonnel)).willReturn(Arrays.asList(pilot, host));
        given(roleRepository.findEmployeesWithRole(pilot)).willReturn(Arrays.asList(aPilot));
        given(roleRepository.findEmployeesWithRole(host)).willReturn(Arrays.asList(aHost));
        given(confirmationRepository.find(any(Employee.class), same(document))).willReturn(null);

    }

    private void whenRequiringConfirmationForDocument() {
        confirmationService.requireConfirmationForDistributedDocuments(Arrays.asList(document));
    }

    private void thenConfirmationsArePersisted() {
        ArgumentCaptor<Confirmation> captor = ArgumentCaptor.forClass(Confirmation.class);
        verify(confirmationRepository, times(employeesInDistributionGroup.size())).insert(captor.capture());
    }

    private void thenConfirmationsArePending() {
        for (Confirmation confirmation : capturedConfirmations()) {
            assertTrue(confirmation.isPending());
        }
    }

    private void thenConfirmationsApplyToDistributedDocument() {
        for (Confirmation confirmation : capturedConfirmations()) {
            assertEquals(document, confirmation.getConfirmedDocument());
        }

    }

    private void thenConfirmationsApplyToAllEmployeesInDistributionGroup() {
        List<Employee> actual = new ArrayList<>();
        for (Confirmation confirmation : capturedConfirmations()) {
            actual.add(confirmation.getConfirmedBy());
        }
        assertEquals(Arrays.asList(aPilot, aHost), actual);
    }

    private List<Confirmation> capturedConfirmations() {
        ArgumentCaptor<Confirmation> captor = ArgumentCaptor.forClass(Confirmation.class);
        verify(confirmationRepository, times(employeesInDistributionGroup.size())).insert(captor.capture());
        return captor.getAllValues();
    }

}
