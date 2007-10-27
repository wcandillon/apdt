package org.phpaspect.apdt.internal.ui.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.php.internal.core.documentModel.parser.regions.PHPRegionTypes;
import org.eclipse.php.internal.ui.editor.highlighter.LineStyleProviderForPhp;
import org.eclipse.php.internal.ui.preferences.PreferenceConstants;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.phpaspect.apdt.internal.core.documentModel.parser.regions.PHPAspectRegionTypes;

public class LineStyleProviderForPHPAspect extends LineStyleProviderForPhp{

	/** Contains region to style mapping */
	private static final Map fColorTypes = new HashMap(); // String (token type), String (color)
	
	static {
		//PHPAspect
		fColorTypes.put(PHPAspectRegionTypes.PHP_ASPECT, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_PERSESSION, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_PERSESSION, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_BEFORE, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_AROUND, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_AFTER, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_POINTCUT, PreferenceConstants.EDITOR_KEYWORD_COLOR);
	}

	/*
	 * Returns hash of color attributes
	 */
	public Map getColorTypesMap() {
		if(fColorTypes.size() == 0){
			initColorTypes();
		}
		return fColorTypes;
	}
	
	private void initColorTypes() {
		//PHP
		fColorTypes.putAll(super.getColorTypesMap());
		//PHPAspect
		fColorTypes.put(PHPAspectRegionTypes.PHP_ASPECT, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_PERSESSION, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_PERSESSION, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_BEFORE, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_AROUND, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_AFTER, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		fColorTypes.put(PHPAspectRegionTypes.PHP_POINTCUT, PreferenceConstants.EDITOR_KEYWORD_COLOR);
	}

	/**
	 * Look up the TextAttribute for the given region context. Might return
	 * null for unusual text.
	 * 
	 * @param type
	 * @return
	 */
	protected TextAttribute getAttributeFor(String type) {
		if(fColorTypes.size() == 0){
			initColorTypes();
		}
		return (TextAttribute) getTextAttributes().get(fColorTypes.get(type));
	}
}