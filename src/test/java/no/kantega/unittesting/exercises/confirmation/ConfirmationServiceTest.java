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

    @Before
    public void setup() {

        roleRepository = mock(RoleRepository.class);
        confirmationRepository = mock(ConfirmationRepository.class);
        confirmationService = new ConfirmationService(roleRepository, confirmationRepository);

    }

    @Test
    public void shallCreatePendingConfirmations() {

        Role pilot = new Role("PILOT");
        Role host = new Role("HOST");
        DistributionGroup onBoardPersonnel = new DistributionGroup();
        given(roleRepository.findRolesForDistributionGroup(onBoardPersonnel)).willReturn(Arrays.asList(pilot, host));

        Employee aPilot = new Employee(1L);
        Employee aHost = new Employee(2L);
        given(roleRepository.findEmployeesWithRole(pilot)).willReturn(Arrays.asList(aPilot));
        given(roleRepository.findEmployeesWithRole(host)).willReturn(Arrays.asList(aHost));

        Document document = new Document(1L);
        document.setDistributionGroup(onBoardPersonnel);

        given(confirmationRepository.find(any(Employee.class), same(document))).willReturn(null);

        confirmationService.requireConfirmationForDistributedDocuments(Arrays.asList(document));

        List<Employee> employeesInDistributionGroup = Arrays.asList(aPilot, aHost);
        ArgumentCaptor<Confirmation> argument = ArgumentCaptor.forClass(Confirmation.class);
        verify(confirmationRepository, times(employeesInDistributionGroup.size())).insert(argument.capture());

        List<Confirmation> confirmations = argument.getAllValues();
        List<Employee> actual = new ArrayList<>();
        for (Confirmation confirmation : confirmations) {
            assertTrue(confirmation.isPending());
            assertEquals(document, confirmation.getConfirmedDocument());
            actual.add(confirmation.getConfirmedBy());
        }
        assertEquals(Arrays.asList(aPilot, aHost), actual);
    }

}
