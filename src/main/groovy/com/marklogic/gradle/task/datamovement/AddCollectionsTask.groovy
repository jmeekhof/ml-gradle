package com.marklogic.gradle.task.datamovement

import com.marklogic.client.DatabaseClient
import com.marklogic.client.ext.datamovement.QueryBatcherTemplate
import com.marklogic.client.ext.datamovement.listener.AddCollectionsListener
import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class AddCollectionsTask extends MarkLogicTask {

	@TaskAction
	void addCollections() {
		if (!project.hasProperty("collections") || !project.hasProperty("targetCollections")) {
			println "Specify the source collections with -Pcollections=comma-separated-list and the " +
				"target collections with -PtargetCollections=comma-separated-list"
			return;
		}

		String[] collections = getProject().property("collections").split(",")
		String[] targetCollections = getProject().property("targetCollections").split(",")

		DatabaseClient client = newClient()
		try {
			String message = "documents in collections: " + Arrays.asList(collections) + " to collections: " + Arrays.asList(targetCollections)
			println "Adding " + message
			new QueryBatcherTemplate(client).applyOnCollections(new AddCollectionsListener(targetCollections), collections);
			println "Finished adding " + message
		} finally {
			client.release()
		}
	}
}
