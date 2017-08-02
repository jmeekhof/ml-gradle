package com.marklogic.gradle.task.datamovement

import com.marklogic.client.datamovement.DeleteListener
import org.gradle.api.tasks.TaskAction

class DeleteCollectionsTask extends DataMovementTask {

	@TaskAction
	void deleteCollections() {
		if (!project.hasProperty("collections")) {
			println "Specify the collections to delete with -Pcollections=comma-separated-list"
			return
		}

		String[] collections = getProject().property("collections").split(",")
		String message = "collections: " + Arrays.asList(collections)
		println "Deleting " + message
		applyOnCollections(new DeleteListener(), collections)
		println "Finished deleting " + message
	}
}
