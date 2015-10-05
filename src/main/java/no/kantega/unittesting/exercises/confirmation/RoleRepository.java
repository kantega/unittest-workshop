package no.kantega.unittesting.exercises.confirmation;

import java.util.List;

public interface RoleRepository {

    List<Role> findRolesForDistributionGroup(DistributionGroup distributionGroup);

    List<Employee> findEmployeesWithRole(Role role);
}
