package org.phpaspect.weaver.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.phpaspect.weaver.Pointcut;
import org.phpaspect.weaver.ast.match.ASTMatcher;
import org.phpaspect.weaver.ast.visitor.Visitor;

public class AspectCodeAdviceDeclaration extends Statement {

	private AdviceType adviceType;
	private List args;
	private Block body;
	private Pointcut pointcut;

	/**
	 * The structural property of this node type.
	 */
	/*public static final ChildPropertyDescriptor ADVICE_TYPE_PROPERTY = 
		new ChildPropertyDescriptor(AspectCodeAdviceDeclaration.class, "adviceType", AdviceType.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor POINTCUT_PROPERTY = 
		new ChildPropertyDescriptor(AspectCodeAdviceDeclaration.class, "pointcut", Pointcut.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor BODY_PROPERTY = 
		new ChildPropertyDescriptor(AspectCodeAdviceDeclaration.class, "body", Block.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	protected ChildPropertyDescriptor getAdviceTypeProperty() {
		return ADVICE_TYPE_PROPERTY;
	}

	protected ChildPropertyDescriptor getPointcutProperty() {
		return POINTCUT_PROPERTY;
	}

	protected ChildPropertyDescriptor getPointcutBody() {
		return BODY_PROPERTY;
	}*/	
	
	/**
	 * A list of property descriptors (element type: 
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;
	
	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<StructuralPropertyDescriptor>(3);
		//propertyList.add(ADVICE_TYPE_PROPERTY);
		//propertyList.add(POINTCUT_PROPERTY);
		//propertyList.add(BODY_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}		
	
	public AspectCodeAdviceDeclaration(int start, int end, AST ast, AdviceType adviceType, Pointcut pointcut, Block body) {
		super(start, end, ast);
		this.adviceType = adviceType;
		this.args = new LinkedList();
		this.pointcut = pointcut;
		this.body = body;
	}
	
	public AdviceType getAdviceType() {
		return adviceType;
	}

	public void setAdviceType(AdviceType adviceType) {
		this.adviceType = adviceType;
	}

	public List getArgs() {
		return args;
	}

	public void setArgs(List args) {
		this.args = args;
	}

	public Block getBody() {
		return body;
	}

	public void setBody(Block body) {
		this.body = body;
	}

	public Pointcut getPointcut() {
		return pointcut;
	}

	public void setPointcut(Pointcut pointcut) {
		this.pointcut = pointcut;
	}

	@Override
	void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);		
	}

	@Override
	ASTNode clone0(AST target) {
		final AdviceType adviceType = getAdviceType();
		final Block body = ASTNode.copySubtree(target, getBody());
		final Pointcut pointcut = getPointcut().clone();
		final AspectCodeAdviceDeclaration result = new AspectCodeAdviceDeclaration(getStart(), getEnd(), target, adviceType, pointcut, body);
		return result;
	}

	@Override
	public int getType() {
		return ASTNode.CODE_ADVICE_DECLARATION;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(
			String apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	public void childrenAccept(Visitor visitor) {
		getBody().accept(visitor);
	}

	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<CodeAdvice"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" type='").append(adviceType.toString()).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(pointcut.toString());
		buffer.append(body.toString());
		buffer.append(tab).append("</CodeAdvice>"); //$NON-NLS-1$
	}

	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		getBody().traverseTopDown(visitor);
	}

	public void traverseBottomUp(Visitor visitor) {
		getBody().traverseBottomUp(visitor);
		accept(visitor);
	}
}
