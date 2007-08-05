package org.phpaspect.apdt.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.php.internal.core.project.PHPNature;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.OverlayIcon;
import org.phpaspect.apdt.builder.PHPAspectNature;

public class APDTDecorator extends LabelProvider implements ILabelDecorator {

	public APDTDecorator(){
		super();
	}
	
	public Image decorateImage(Image image, Object object) {
		//See extension org.phpaspect.apdt.APDTDecorator
		IResource ressource = (IResource)object;
		//If the project has the PHPAspect nature, we decorate the project folder and the aspect files
		try {
			if(ressource.getProject().hasNature(PHPAspectNature.NATURE_ID) &&
					ressource.getType() == IResource.PROJECT){
				return null;
			}
		} catch (CoreException e) {
			// TODO Do something ?
			e.printStackTrace();
		}
		//Returning a null image decorates a resource icon
		//with basic decorations provided by Eclipse
		return null;
	}

	public String decorateText(String label, Object object) {
		//Returning a null label decorates a resource label
		//with basic label provided by Eclipse
		return null;
	}

}