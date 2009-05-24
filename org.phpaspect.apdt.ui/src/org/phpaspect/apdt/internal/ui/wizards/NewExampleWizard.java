package org.phpaspect.apdt.internal.ui.wizards;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.phpaspect.apdt.ui.APDTUiPlugin;

public class NewExampleWizard extends PHPAspectProjectCreationWizard implements IExecutableExtension
{
	private String example;

	@SuppressWarnings("restriction")
	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
	{
		super.setInitializationData(config, propertyName, data);
		if(config.getName().equals("wizard") && propertyName.equals("class") &&
				data instanceof String)
		{
			example = (String)data;
		}
	}
	
	@SuppressWarnings("restriction")
	@Override
	public void addPages()
	{
		super.addPages();
		fFirstPage.setTitle("PHPAspect Example Project");
		fFirstPage.setDescription("New PHPAspect Example Project");	
	}
	
	@Override
	public boolean performFinish()
	{
		boolean finished = super.performFinish();
		if(finished)
		{
			IProject project = fLastPage.getScriptProject().getProject();
			//Copy the files in the weaved folder
		    String bundleLocation = APDTUiPlugin.getDefault().getBundle().getLocation();
		    bundleLocation = bundleLocation.substring(10);
		    URL files = null;
			try {
				files = new URL(bundleLocation+"Examples/"+example);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

		    File src = null;
		    try {
		    	src = new File(files.toURI());
		    } catch (URISyntaxException e) {
		    	src = new File(files.getPath());
			}
		    Examples.run(project, example);
		}
		return finished;
	}
}
