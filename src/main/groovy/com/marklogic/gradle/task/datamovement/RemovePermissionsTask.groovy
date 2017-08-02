package com.marklogic.gradle.task.datamovement

import com.marklogic.client.ext.datamovement.listener.AddPermissionsListener
import com.marklogic.client.ext.datamovement.listener.RemovePermissionsListener
import org.gradle.api.tasks.TaskAction

class RemovePermissionsTask extends DataMovementTask {

	@TaskAction
	void removePermissions() {
		if (!project.hasProperty("collections") || !project.hasProperty("permissions")) {
			println "Specify the collections of documents to remove permissions from with -Pcollections=comma-separated-list " +
				"and the permissions with -Ppermissions=comma-separated-roles-and-capabilities"
			return;
		}

		String[] collections = getProject().property("collections").split(",")
		String permissions = getProject().property("permissions")

		String message = "permissions " + permissions + " from documents in collections: " + Arrays.asList(collections)
		println "Removing " + message
		applyOnCollections(new RemovePermissionsListener(permissions), collections)
		println "Finished removing " + message
	}
}

