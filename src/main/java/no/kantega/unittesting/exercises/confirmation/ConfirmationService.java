package no.kantega.unittesting.exercises.confirmation;

import java.util.*;

public class ConfirmationService {

    private RoleRepository roleRepository;
    private ConfirmationRepository confirmationRepository;

    public ConfirmationService(RoleRepository roleRepository, ConfirmationRepository confirmationRepository) {
        this.roleRepository = roleRepository;
        this.confirmationRepository = confirmationRepository;
    }

    void requireConfirmationForDistributedDocuments(List<Document> distributedDocuments) {

        Set<DistributionGroup> distributionGroups = new HashSet<DistributionGroup>();
        for (Document document : distributedDocuments) {
            distributionGroups.add(document.getDistributionGroup());
        }

        Map<DistributionGroup, Map<Long, Employee>> employeesInDistributionGroup = new HashMap<DistributionGroup, Map<Long, Employee>>();
        for (DistributionGroup distributionGroup : distributionGroups) {
            Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
            List<Role> roles = roleRepository.findRolesForDistributionGroup(distributionGroup);
            for (Role role : roles) {
                List<Employee> employees = roleRepository.findEmployeesWithRole(role);
                for (Employee employee : employees) {
                    employeeMap.put(employee.getId(), employee);
                }
            }
            employeesInDistributionGroup.put(distributionGroup, employeeMap);
        }

        for (Document document : distributedDocuments) {
            Map<Long, Employee> employeeMap = employeesInDistributionGroup.get(document.getDistributionGroup());
            for (Employee employee : employeeMap.values()) {
                Confirmation confirmation = confirmationRepository.find(employee, document);
                if (confirmation == null) {
                    confirmationRepository.insert(new Confirmation(document, employee));
                }
            }
        }
    }

}
