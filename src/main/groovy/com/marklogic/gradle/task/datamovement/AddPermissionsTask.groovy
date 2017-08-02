package com.marklogic.gradle.task.datamovement

import com.marklogic.client.ext.datamovement.listener.AddPermissionsListener
import org.gradle.api.tasks.TaskAction

class AddPermissionsTask extends DataMovementTask {

	@TaskAction
	void addPermissions() {
		if (!project.hasProperty("collections") || !project.hasProperty("permissions")) {
			println "Specify the collections of documents to add permissions to with -Pcollections=comma-separated-list " +
				"and the permissions with -Ppermissions=comma-separated-roles-and-capabilities"
			return;
		}

		String[] collections = getProject().property("collections").split(",")
		String permissions = getProject().property("permissions")

		String message = "permissions " + permissions + " to documents in collections: " + Arrays.asList(collections)
		println "Adding " + message
		applyOnCollections(new AddPermissionsListener(permissions), collections)
		println "Finished adding " + message
	}
}
