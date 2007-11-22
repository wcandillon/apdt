package org.phpaspect.apdt.internal.ui.treecontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.php.core.documentModel.IWorkspaceModelListener;
import org.eclipse.php.internal.core.PHPCoreConstants;
import org.eclipse.php.internal.core.phpModel.PHPModelUtil;
import org.eclipse.php.internal.core.phpModel.parser.*;
import org.eclipse.php.internal.core.phpModel.phpElementData.CodeData;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPFileData;
import org.eclipse.php.internal.core.project.options.PHPProjectOptions;
import org.eclipse.php.internal.core.project.options.includepath.IncludePathVariableManager;
import org.eclipse.php.internal.ui.PHPUiPlugin;
import org.eclipse.php.internal.ui.treecontent.IncludePathTreeContent;
import org.eclipse.php.internal.ui.treecontent.IncludesNode;
import org.eclipse.php.internal.ui.treecontent.PHPTreeNode;
import org.eclipse.php.internal.ui.util.PHPPluginImages;
import org.eclipse.php.ui.treecontent.IPHPTreeContentProvider;
import org.eclipse.swt.graphics.Image;
import org.phpaspect.apdt.internal.core.builder.PHPAspectNature;

/**
 * A content provider for include paths under projects. Shows files in file-system-like tree.
 * @author seva
 */
public class PHPAspectTreeContent implements IPHPTreeContentProvider{

	private static final String PHPASPECT_RUNTIME_NODE_ID   = "org.phpaspect.apdt.ui.treecontent.PHPAspectRuntimeNode"; //$NON-NLS-1$
	private static final String PHPASPECT_RUNTIME_NODE_NAME = "PHPAspect Runtime Library"; //$NON-NLS-1$

	static final IPath PHPASPECT_RUNTIME_ROOT_PATH = new Path("\0PHPAspectRuntime"); //$NON-NLS-1$

	public Object[] getChildren(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getParent(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasChildren(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object[] getElements(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	public Image getImage(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getText(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}
}