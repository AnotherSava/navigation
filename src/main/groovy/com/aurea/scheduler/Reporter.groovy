package com.aurea.scheduler

import one.util.streamex.StreamEx

class Reporter {
    static void reportSession(PlannedSession plannedSession) {
        System.out.println("$plannedSession.session, $plannedSession.dayStart-$plannedSession.dayFinish")
    }

    private static String createPersonStatusWithProjects(Scheduler scheduler, Person person) {
        StreamEx.of(scheduler.projects.projects).map { getPersonStatusWithTheProject(person, it) }.joining("  ")
    }

    static void reportState(Scheduler scheduler) {
        scheduler.persons.forEach { System.out.println(
                createPersonStatusWithProjects(scheduler, it) + "     " + it + ",  remaining workload: " + scheduler.remainingWorkloadForPerson(it))}
    }

    private static String getPersonStatusWithTheProject(Person person, Project project) {
        person.canNavigate(project) ? "N" : person.needNavigation(project) ? "-" : "+"
    }
}
