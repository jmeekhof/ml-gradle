package com.marklogic.gradle.task.datamovement

import com.marklogic.client.ext.datamovement.listener.SetCollectionsListener
import org.gradle.api.tasks.TaskAction

class SetCollectionsTask extends DataMovementTask {

	@TaskAction
	void setCollections() {
		if (!project.hasProperty("collections") || !project.hasProperty("targetCollections")) {
			println "Specify the source collections with -Pcollections=comma-separated-list and the " +
				"target collections with -PtargetCollections=comma-separated-list"
			return;
		}

		String[] collections = getProject().property("collections").split(",")
		String[] targetCollections = getProject().property("targetCollections").split(",")

		String message = "documents in collections: " + Arrays.asList(collections) + " to collections: " + Arrays.asList(targetCollections)
		println "Setting " + message
		applyOnCollections(new SetCollectionsListener(targetCollections), collections)
		println "Finished setting " + message
	}
}
