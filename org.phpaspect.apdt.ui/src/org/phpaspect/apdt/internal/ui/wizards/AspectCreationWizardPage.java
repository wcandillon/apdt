package org.phpaspect.apdt.internal.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.phpaspect.apdt.internal.ui.icons.APDTPluginImages;
import org.phpaspect.apdt.internal.ui.text.UIMessages;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (ap).
 * @TODO Checking the project (and the php5 setting ?).
 * @TODO NLS
 */

public class AspectCreationWizardPage extends WizardPage{
	private Text containerText;

	private Text fileText;
	
	private Text aspectText;

	private ISelection selection;

	private Button modAbstractButton;

	private Button modFinalButton;

	private Button insSingletonButton;

	private Button insSessionButton;

	private Button insFromButton;

	private Text extendsText;

	private Text implementsText;

	private Button modDefaultButton;

	private Text nsText;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public AspectCreationWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle(UIMessages.NewAspectCreationWizardPage_title);
		setDescription(UIMessages.NewAspectCreationWizardPage_description);
		setImageDescriptor(APDTPluginImages.DESC_WIZBAN_NEW_ASPECT);
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		int ncol = 4;
		
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Folder:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		WizardsUtils.createBlank(container);

		WizardsUtils.createLine(container, ncol);
		
		label = new Label(container, SWT.NULL);
		label.setText("&Aspect name:");

		aspectText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		aspectText.setLayoutData(gd);
		aspectText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		WizardsUtils.createBlank(container);
		
//		label = new Label(container, SWT.NULL);
//		label.setText("&Namespace:");
//
//		nsText = new Text(container, SWT.BORDER | SWT.SINGLE);
//		gd = new GridData(GridData.FILL_HORIZONTAL);
//		nsText.setLayoutData(gd);
//		nsText.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent e) {
//				dialogChanged();
//			}
//		});
//		
//		WizardsUtils.createBlank(container);
		
		new Label(container, SWT.NONE).setText("Modifier:"); //$NON-NLS-1$
		
		Composite modComposite = new Composite(container, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 2;
		modComposite.setLayoutData(gd);

		GridLayout mgl = new GridLayout();
		mgl.marginWidth = 0;
		mgl.marginHeight = 0;
		mgl.numColumns = 3;
		modComposite.setLayout(mgl);

		modDefaultButton = new Button(modComposite, SWT.RADIO);
		modDefaultButton.setSelection(true);
		modDefaultButton.setText("default"); //$NON-NLS-1$
		
		modAbstractButton = new Button(modComposite, SWT.RADIO);
		modAbstractButton.setSelection(false);
		modAbstractButton.setText("abstract"); //$NON-NLS-1$

		modFinalButton = new Button(modComposite, SWT.RADIO);
		modFinalButton.setSelection(false);
		modFinalButton.setText("final"); //$NON-NLS-1$
		
		new Label(container, SWT.NONE).setText("Instanciation:"); //$NON-NLS-1$

		Composite insComposite = new Composite(container, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 2;
		insComposite.setLayoutData(gd);

		GridLayout igl = new GridLayout();
		igl.marginWidth = 0;
		igl.marginHeight = 0;
		igl.numColumns = 3;
		insComposite.setLayout(igl);
		
		insSingletonButton = new Button(insComposite, SWT.RADIO);
		insSingletonButton.setSelection(true);
		insSingletonButton.setText("singleton"); //$NON-NLS-1$
		
		insSessionButton = new Button(insComposite, SWT.RADIO);
		insSessionButton.setSelection(false);
		insSessionButton.setText("persession"); //$NON-NLS-1$
		
		insFromButton = new Button(insComposite, SWT.RADIO);
		insFromButton.setSelection(false);
		insFromButton.setText("from"); //$NON-NLS-1$
	    
		new Label(container, SWT.NONE).setText("Supertype:"); //$NON-NLS-1$
		
		extendsText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol-3;
		gd.grabExcessHorizontalSpace = true;
		extendsText.setLayoutData(gd);
		extendsText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		WizardsUtils.createBlank(container);
		
		new Label(container, SWT.NONE).setText("Interfaces:"); //$NON-NLS-1$
		
		implementsText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol-3;
		gd.grabExcessHorizontalSpace = true;
		implementsText.setLayoutData(gd);
		implementsText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		new Label(container, SWT.NONE).setText("(comma-separated)");
		
		initialize();
		dialogChanged();
		setControl(container);
	}
	
	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		//fileText.setText("");
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new Folder");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}
	
	/**
	 * Ensures that both text fields are set.
	 */
	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
		String fileName = getFileName();
		String aspectName = getAspectName();
		String superTypeName = getSuperTypeName();
		String[] interfaces = getInterfaces();

		if (getContainerName().length() == 0) {
			updateStatus("Folder must be specified");
			return;
		}
		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("Folder must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("ap") == false) {
				updateStatus("File extension must be \"ap\"");
				return;
			}
		}else{
			updateStatus("File extension must be \"ap\"");
			return;
		}
		if (aspectName.length() == 0) {
			updateStatus("Aspect name must be specified");
			return;
		}
		if (!WizardsUtils.isValidPHPTypeName(aspectName)) {
			updateStatus("Aspect name must be valid");
			return;
		}
		if (superTypeName.length()>0 && !WizardsUtils.isValidPHPTypeName(superTypeName)) {
			updateStatus("Super type name must be valid");
			return;
		}
		for(int i=0; i<interfaces.length; i++){
			if (interfaces[i].length()>0 && 
					!WizardsUtils.isValidPHPTypeName(interfaces[i].trim())) {
				updateStatus("Invalid interface name: "+interfaces[i].trim());
				return;
			}
		}
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName(){
		return fileText.getText();
	}
	
	public String getAspectName() {
		return aspectText.getText();
	}
	
	public String getSuperTypeName(){
		return extendsText.getText();
	}
	
	public String[] getInterfaces(){
		return implementsText.getText().split(",");
	}
	
	public String getAspect(){
		String aspect = "<?php\n";
		if(modAbstractButton.getSelection()){ aspect += "abstract "; }
		if(modFinalButton.getSelection()){ aspect += "final "; }
		aspect += "aspect "+getAspectName();
		if(getSuperTypeName().length()>0){
			aspect += " extends "+getSuperTypeName();
		}
		String[] interfaces = getInterfaces();
		if(interfaces.length > 0 && interfaces[0].length() > 0){
			aspect += " implements";
			for(int i=0; i<interfaces.length; i++){
				aspect += " "+interfaces[i].trim();
				if(i != interfaces.length-1){
					aspect += ',';
				}
			}
		}
		if(insSessionButton.getSelection()){ aspect += " perSession"; }
		if(insFromButton.getSelection()){ aspect += " from(\"url://pattern\")"; }
		return aspect+"{\n}\n?>";
	}
}