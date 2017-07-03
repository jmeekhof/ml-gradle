package com.marklogic.gradle.task.datamovement

import com.marklogic.client.DatabaseClient
import com.marklogic.client.datamovement.DeleteListener
import com.marklogic.client.ext.datamovement.QueryBatcherTemplate
import com.marklogic.gradle.task.MarkLogicTask
import org.gradle.api.tasks.TaskAction

class AddCollectionsTask extends MarkLogicTask {

	/**
	 * mlAddCollections -PsourceCollections=test1 -PtargetCollections=
	 */
	@TaskAction
	void addCollections() {
		String[] collections = getProject().property("collections").split(",")
		String[] targetCollections = getProject().property("targetCollections").split(",")
		DatabaseClient client = newClient()
		QueryBatcherTemplate qbt = new QueryBatcherTemplate(client)
		println "Deleting collections: " + Arrays.asList(collections)
		qbt.applyOnCollections(new DeleteListener(), collections);
		println "Finished deleting collections: " + Arrays.asList(collections)

	}
}
