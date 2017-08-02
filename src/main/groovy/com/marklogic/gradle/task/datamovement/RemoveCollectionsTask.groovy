package com.marklogic.gradle.task.datamovement

import com.marklogic.client.ext.datamovement.listener.RemoveCollectionsListener
import org.gradle.api.tasks.TaskAction

class RemoveCollectionsTask extends DataMovementTask {

	/**
	 * This task allows for specifying source collections for documents that should be removed from target collections,
	 * but it's often the case that that list of collections is the same (and often just one collection). Thus, only the
	 * "collections" property needs to be specified if the lists are the same.
	 */
	@TaskAction
	void removeCollections() {
		if (!project.hasProperty("collections")) {
			println "Specify the source collections with -Pcollections=comma-separated-list and optionally the " +
				"target collections with -PtargetCollections=comma-separated-list; if 'targetCollections' is not set, then it will be the same as 'collections'"
			return;
		}

		String[] collections = getProject().property("collections").split(",")
		String[] targetCollections = collections
		if (project.hasProperty("targetCollections")) {
			targetCollections = getProject().property("targetCollections").split(",")
		}

		String message = "in collections: " + Arrays.asList(collections) + " from collections: " + Arrays.asList(targetCollections)
		println "Removing documents " + message
		applyOnCollections(new RemoveCollectionsListener(targetCollections), collections);
		println "Finished removing documents " + message
	}
}
