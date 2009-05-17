package org.phpaspect.apdt.internal.ui.editor.highlighter;

import java.util.Collection;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.php.internal.core.documentModel.parser.PHPRegionContext;
import org.eclipse.php.internal.core.documentModel.parser.regions.PHPRegionTypes;
import org.eclipse.php.internal.ui.Logger;
import org.eclipse.php.internal.ui.editor.highlighter.LineStyleProviderForPhp;
import org.eclipse.php.internal.ui.preferences.PreferenceConstants;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionCollection;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.sse.core.internal.util.Debug;
import org.phpaspect.apdt.internal.core.documentModel.parser.regions.PHPAspectRegion;
import org.phpaspect.apdt.internal.core.documentModel.parser.regions.PHPAspectRegionTypes;

public class LineStyleProviderForPHPAspect extends LineStyleProviderForPhp{

	public LineStyleProviderForPHPAspect(){
		initColorTypes();
	}
	
	private void initColorTypes() {
		//PHPAspect
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_ASPECT, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_PERSESSION, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_PERSESSION, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_BEFORE, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_AROUND, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_AFTER, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_POINTCUT, PreferenceConstants.EDITOR_KEYWORD_COLOR);
		super.getColorTypesMap().put(PHPAspectRegionTypes.PHP_PARENTS, PreferenceConstants.EDITOR_KEYWORD_COLOR);
	}
	
	/**
	 * @param region
	 * @param start
	 * @param length
	 * @param holdResults
	 * @return
	 */
	public boolean prepareTextRegion(ITextRegionCollection blockedRegion, int partitionStartOffset, int partitionLength, Collection holdResults) {
		boolean handled = false;
		final int partitionEndOffset = partitionStartOffset + partitionLength - 1;
		ITextRegion region = null;
		ITextRegionList regions = blockedRegion.getRegions();
		int nRegions = regions.size();
		StyleRange styleRange = null;
		for (int i = 0; i < nRegions; i++) {
			region = regions.get(i);
			TextAttribute attr = null;
			TextAttribute previousAttr = null;
			final int startOffset = blockedRegion.getStartOffset(region);
			if (startOffset > partitionEndOffset)
				break;
			if (blockedRegion.getEndOffset(region) <= partitionStartOffset)
				continue;

			if (region instanceof ITextRegionCollection) {
				handled = prepareTextRegion((ITextRegionCollection) region, partitionStartOffset, partitionLength, holdResults);
			} else {

				if (region.getType() == PHPRegionContext.PHP_CONTENT) {
					handled = preparePhpRegions(holdResults, (PHPAspectRegion) region, startOffset, partitionStartOffset, partitionLength);
				} else {
					attr = getAttributeFor(region);
					if (attr != null) {
						handled = true;
						// if this region's attr is the same as previous one, then
						// just adjust the previous style range
						// instead of creating a new instance of one
						// note: to use 'equals' in this case is important, since
						// sometimes
						// different instances of attributes are associated with a
						// region, even the
						// the attribute has the same values.
						// TODO: this needs to be improved to handle readonly
						// regions correctly
						if ((styleRange != null) && (previousAttr != null) && (previousAttr.equals(attr))) {
							styleRange.length += region.getLength();
						} else {
							styleRange = createStyleRange(blockedRegion, region, attr, partitionStartOffset, partitionLength);
							holdResults.add(styleRange);
							// technically speaking, we don't need to update
							// previousAttr
							// in the other case, because the other case is when
							// it hasn't changed
							previousAttr = attr;
						}
					} else {
						previousAttr = null;
					}
				}
			}
		}
		return handled;
	}

	public boolean prepareTextRegions(IStructuredDocumentRegion structuredDocumentRegion, int partitionStartOffset, int partitionLength, Collection holdResults) {
		boolean handled = false;
		final int partitionEndOffset = partitionStartOffset + partitionLength - 1;
		while (structuredDocumentRegion != null && structuredDocumentRegion.getStartOffset() <= partitionEndOffset) {
			ITextRegion region = null;
			ITextRegionList regions = structuredDocumentRegion.getRegions();
			int nRegions = regions.size();
			StyleRange styleRange = null;
			for (int i = 0; i < nRegions; i++) {
				region = regions.get(i);
				TextAttribute attr = null;
				TextAttribute previousAttr = null;
				final int startOffset = structuredDocumentRegion.getStartOffset(region);
				if (startOffset > partitionEndOffset)
					break;
				if (structuredDocumentRegion.getEndOffset(region) <= partitionStartOffset)
					continue;

				if (region instanceof ITextRegionCollection) {
					handled = prepareTextRegion((ITextRegionCollection) region, partitionStartOffset, partitionLength, holdResults);
				} else {

					if (region.getType() == PHPRegionContext.PHP_CONTENT) {
						handled = preparePhpRegions(holdResults, (PHPAspectRegion) region, startOffset, partitionStartOffset, partitionLength);
					} else {

						attr = getAttributeFor(region);
						if (attr != null) {
							handled = true;
							// if this region's attr is the same as previous one,
							// then just adjust the previous style range
							// instead of creating a new instance of one
							// note: to use 'equals' in this case is important,
							// since sometimes
							// different instances of attributes are associated
							// with a region, even the
							// the attribute has the same values.
							// TODO: this needs to be improved to handle readonly
							// regions correctly
							if ((styleRange != null) && (previousAttr != null) && (previousAttr.equals(attr))) {
								styleRange.length += region.getLength();
							} else {
								styleRange = createStyleRange(structuredDocumentRegion, region, attr, partitionStartOffset, partitionLength);
								holdResults.add(styleRange);
								// technically speaking, we don't need to update
								// previousAttr
								// in the other case, because the other case is
								// when it hasn't changed
								previousAttr = attr;
							}
						} else {
							previousAttr = null;
						}
					}

				}

				if (Debug.syntaxHighlighting && !handled) {
					System.out.println("not handled in prepareRegions"); //$NON-NLS-1$
				}
			}
			structuredDocumentRegion = structuredDocumentRegion.getNext();
		}
		return handled;
	}
	
	/**
	 * Prepares php regions to the line highliter
	 * @param holdResults - results
	 * @param region - php region
	 * @param partitionLength 
	 * @param partitionStartOffset 
	 */
	private boolean preparePhpRegions(Collection holdResults, PHPAspectRegion region, int regionStart, int partitionStartOffset, int partitionLength) {
		assert region.getType() == PHPRegionContext.PHP_CONTENT;

		StyleRange styleRange = null;
		TextAttribute attr;
		TextAttribute previousAttr = null;

		ITextRegion[] phpTokens = null;
		try {

			int from;
			int length;
			if (partitionStartOffset < regionStart) {
				from = 0;
				length = partitionLength - (partitionStartOffset - regionStart);
			} else {
				from = partitionStartOffset - regionStart;
				length = partitionLength;
			}
			phpTokens = region.getPhpTokens(from, Math.min(length, region.getLength()));
			ITextRegion prevElement = null;
			for (int i = 0; i < phpTokens.length; i++) {
				ITextRegion element = phpTokens[i];
				// ignore any first whitespace regions
				if (i == 0 && (element.getType() == PHPRegionTypes.WHITESPACE || element.getTextEnd() < from)) {
					continue;
				}
				attr = getAttributeFor(element);
				if ((styleRange != null) && (previousAttr != null) && (previousAttr.equals(attr)) && prevElement != null && prevElement.getTextLength() == prevElement.getLength()) {
					// extends the prev styleRange with the current element length
					styleRange.length += element.getTextLength();
				} else {
					// create new styleRange
					styleRange = new StyleRange(regionStart + element.getStart(), element.getTextLength(), attr.getForeground(), attr.getBackground(), attr.getStyle());
					if ((attr.getStyle() & TextAttribute.UNDERLINE) != 0) {
						styleRange.underline = true;
						styleRange.fontStyle &= ~TextAttribute.UNDERLINE;
					}
					if ((attr.getStyle() & TextAttribute.STRIKETHROUGH) != 0) {
						styleRange.strikeout = true;
						styleRange.fontStyle &= ~TextAttribute.STRIKETHROUGH;
					}
					holdResults.add(styleRange);
					// technically speaking, we don't need to update
					// previousAttr
					// in the other case, because the other case is when
					// it hasn't changed
					previousAttr = attr;
				}
				prevElement = element;
			}
			return true;
		} catch (BadLocationException e) {
			Logger.logException(e);
			return false;
		}
	}
}