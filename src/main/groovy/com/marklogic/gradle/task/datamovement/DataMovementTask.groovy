package com.marklogic.gradle.task.datamovement

import com.marklogic.client.DatabaseClient
import com.marklogic.client.datamovement.QueryBatchListener
import com.marklogic.client.ext.datamovement.QueryBatcherTemplate
import com.marklogic.client.ext.datamovement.listener.AddCollectionsListener
import com.marklogic.gradle.task.MarkLogicTask

class DataMovementTask extends MarkLogicTask {

	void applyOnCollections(QueryBatchListener listener, String... collections) {
		DatabaseClient client = newClient()
		try {
			new QueryBatcherTemplate(client).applyOnCollections(listener, collections);
		} finally {
			client.release()
		}
	}
}
