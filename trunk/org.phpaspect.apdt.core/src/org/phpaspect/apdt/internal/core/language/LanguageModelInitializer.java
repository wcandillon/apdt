package org.phpaspect.apdt.internal.core.language;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IBuiltinModuleProvider;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.EnvironmentPathUtils;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.php.internal.core.Logger;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;

public class LanguageModelInitializer extends BuildpathContainerInitializer {

	public static final String PHPASPECT_LANGUAGE_LIBRARY = "PHPAspect Runtime Language Library";

	/**
	 * Path of the language model for php projects
	 */
	public static final String CONTAINER_PATH = APDTCorePlugin.PLUGIN_ID + ".RUNTIME_LANGUAGE"; //$NON-NLS-1$
	private static final String LANGUAGE_LIBRARY_PATH = "Resources/Runtime"; //$NON-NLS-1$
	
	public LanguageModelInitializer() {}

	public void initialize(IPath containerPath, IScriptProject project) throws CoreException {
		try {
			if (isPHPAspectProject(project)) {
				DLTKCore.setBuildpathContainer(containerPath, new IScriptProject[] { project }, new IBuildpathContainer[] { new LanguageModelContainer(containerPath) }, null);
			}
		} catch (Exception e) {
			Logger.logException(e);
		}
	}

	class LanguageModelContainer implements IBuildpathContainer {

		private IPath containerPath;
		private IBuildpathEntry[] buildPathEntries;

		public LanguageModelContainer(IPath containerPath) {
			this.containerPath = containerPath;
		}

		public IBuildpathEntry[] getBuildpathEntries(IScriptProject project) {
			if (buildPathEntries == null) {
				IEnvironment environment = EnvironmentManager.getEnvironment(project);
				try {
					IPath path = new Path(LANGUAGE_LIBRARY_PATH);
					if (environment != null) {
						path = EnvironmentPathUtils.getFullPath(environment, path);
					}
					buildPathEntries = new IBuildpathEntry[] { DLTKCore.newLibraryEntry(path, BuildpathEntry.NO_ACCESS_RULES, BuildpathEntry.NO_EXTRA_ATTRIBUTES, BuildpathEntry.INCLUDE_ALL, BuildpathEntry.EXCLUDE_NONE, false, true) };
				} catch (Exception e) {
					Logger.logException(e);
				}
			}
			return buildPathEntries;
		}
		
		public IBuiltinModuleProvider getBuiltinProvider(IScriptProject project) {
			return null;
		}

		public String getDescription(IScriptProject project) {
			return PHPASPECT_LANGUAGE_LIBRARY;
		}

		public int getKind() {
			return K_SYSTEM;
		}

		public IPath getPath() {
			return containerPath;
		}
	}

	private static boolean isPHPAspectProject(IScriptProject project) {
		String nature = getNatureFromProject(project);
		return APDTCorePlugin.PLUGIN_ID.equals(nature);
	}

	private static String getNatureFromProject(IScriptProject project) {
		IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager.getLanguageToolkit(project);
		if (languageToolkit != null) {
			return languageToolkit.getNatureId();
		}
		return null;
	}
}
