package com.marklogic.gradle.task.datamovement

import com.marklogic.client.DatabaseClient
import com.marklogic.client.datamovement.DeleteListener
import com.marklogic.client.ext.datamovement.QueryBatcherTemplate
import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class DeleteCollectionsTask extends MarkLogicTask {

	@TaskAction
	void deleteCollections() {
		if (!project.hasProperty("collections")) {
			println "Specify the collections to delete with -Pcollections=comma-separated-list"
			return
		}

		String[] collections = getProject().property("collections").split(",")

		DatabaseClient client = newClient()
		try {
			String message = "collections: " + Arrays.asList(collections)
			println "Deleting " + message
			new QueryBatcherTemplate(client).applyOnCollections(new DeleteListener(), collections);
			println "Finished deleting " + message
		} finally {
			client.release()
		}
	}
}
