package org.phpaspect.apdt.internal.ui.wizards;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.phpaspect.apdt.core.APDTCorePlugin;
import org.phpaspect.apdt.ui.APDTUiPlugin;

public abstract class Examples {
	
	private static final String FILES_PATH = "/Examples/"; //$NON-NLS-1$	
	
	private static final String ORDER_FILE1 = "Order/Log.ap"; //$NON-NLS-1$	
	private static final String ORDER_FILE2 = "Order/Order.php"; //$NON-NLS-1$
	
	private static final String PLAYLISTS_FILE1 = "Playlists/BillingService.ap"; //$NON-NLS-1$	
	private static final String PLAYLISTS_FILE2 = "Playlists/index.php"; //$NON-NLS-1$	
	private static final String PLAYLISTS_FILE3 = "Playlists/Playlist.php"; //$NON-NLS-1$	
	private static final String PLAYLISTS_FILE4 = "Playlists/Song.php"; //$NON-NLS-1$	

	private static final String SINGLETON_FILE1 = "Singleton/test.php"; //$NON-NLS-1$	
	private static final String SINGLETON_FILE2 = "Singleton/Singleton.ap"; //$NON-NLS-1$
	
	public static void run(IProject project, String example)
	{
		try {
			if("Singleton".equals(example))
			{
				createFile(project, FILES_PATH, SINGLETON_FILE1);
				createFile(project, FILES_PATH, SINGLETON_FILE2);
			} else if("Order".equals(example)) {
				createFile(project, FILES_PATH, ORDER_FILE1);
				createFile(project, FILES_PATH, ORDER_FILE2);
			} else if("Playlists".equals(example)) {
				createFile(project, FILES_PATH, PLAYLISTS_FILE1);
				createFile(project, FILES_PATH, PLAYLISTS_FILE2);
				createFile(project, FILES_PATH, PLAYLISTS_FILE3);
				createFile(project, FILES_PATH, PLAYLISTS_FILE4);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static IFile createFile(final IProject project, String filePath, String fileName) throws IOException, CoreException {
		URL demoFileURL = FileLocator.find(Platform.getBundle(APDTUiPlugin.PLUGIN_ID), new Path(filePath + fileName), null); 
		demoFileURL = FileLocator.resolve(demoFileURL);
		IPath p = project.getFullPath();
		IPath destination = new Path(fileName);
		p = p.append(destination.removeFirstSegments(1));
		final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
		InputStream inputStream = (InputStream) demoFileURL.getContent();
		file.create(inputStream, true, null);
		return file;
	}
}
