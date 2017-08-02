package com.marklogic.gradle.task.datamovement

import com.marklogic.client.ext.datamovement.listener.SetPermissionsListener
import org.gradle.api.tasks.TaskAction

class SetPermissionsTask extends DataMovementTask {

	@TaskAction
	void setPermissions() {
		if (!project.hasProperty("collections") || !project.hasProperty("permissions")) {
			println "Specify the collections of documents to set permissions on with -Pcollections=comma-separated-list " +
				"and the permissions with -Ppermissions=comma-separated-roles-and-capabilities"
			return;
		}

		String[] collections = getProject().property("collections").split(",")
		String permissions = getProject().property("permissions")

		String message = "permissions " + permissions + " on documents in collections: " + Arrays.asList(collections)
		println "Setting " + message
		applyOnCollections(new SetPermissionsListener(permissions), collections)
		println "Finished setting " + message
	}
}
