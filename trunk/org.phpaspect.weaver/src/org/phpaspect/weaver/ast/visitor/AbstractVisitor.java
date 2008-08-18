package org.phpaspect.weaver.ast.visitor;

import org.phpaspect.weaver.ast.nodes.ASTError;
import org.phpaspect.weaver.ast.nodes.ASTNode;
import org.phpaspect.weaver.ast.nodes.ArrayAccess;
import org.phpaspect.weaver.ast.nodes.ArrayCreation;
import org.phpaspect.weaver.ast.nodes.ArrayElement;
import org.phpaspect.weaver.ast.nodes.AspectDeclaration;
import org.phpaspect.weaver.ast.nodes.Assignment;
import org.phpaspect.weaver.ast.nodes.BackTickExpression;
import org.phpaspect.weaver.ast.nodes.Block;
import org.phpaspect.weaver.ast.nodes.BreakStatement;
import org.phpaspect.weaver.ast.nodes.CastExpression;
import org.phpaspect.weaver.ast.nodes.CatchClause;
import org.phpaspect.weaver.ast.nodes.ClassConstantDeclaration;
import org.phpaspect.weaver.ast.nodes.ClassDeclaration;
import org.phpaspect.weaver.ast.nodes.ClassInstanceCreation;
import org.phpaspect.weaver.ast.nodes.ClassName;
import org.phpaspect.weaver.ast.nodes.CloneExpression;
import org.phpaspect.weaver.ast.nodes.Comment;
import org.phpaspect.weaver.ast.nodes.ConditionalExpression;
import org.phpaspect.weaver.ast.nodes.ContinueStatement;
import org.phpaspect.weaver.ast.nodes.DeclareStatement;
import org.phpaspect.weaver.ast.nodes.DoStatement;
import org.phpaspect.weaver.ast.nodes.EchoStatement;
import org.phpaspect.weaver.ast.nodes.EmptyStatement;
import org.phpaspect.weaver.ast.nodes.ExpressionStatement;
import org.phpaspect.weaver.ast.nodes.FieldAccess;
import org.phpaspect.weaver.ast.nodes.FieldsDeclaration;
import org.phpaspect.weaver.ast.nodes.ForEachStatement;
import org.phpaspect.weaver.ast.nodes.ForStatement;
import org.phpaspect.weaver.ast.nodes.FormalParameter;
import org.phpaspect.weaver.ast.nodes.FunctionDeclaration;
import org.phpaspect.weaver.ast.nodes.FunctionInvocation;
import org.phpaspect.weaver.ast.nodes.FunctionName;
import org.phpaspect.weaver.ast.nodes.GlobalStatement;
import org.phpaspect.weaver.ast.nodes.Identifier;
import org.phpaspect.weaver.ast.nodes.IfStatement;
import org.phpaspect.weaver.ast.nodes.IgnoreError;
import org.phpaspect.weaver.ast.nodes.InLineHtml;
import org.phpaspect.weaver.ast.nodes.Include;
import org.phpaspect.weaver.ast.nodes.InfixExpression;
import org.phpaspect.weaver.ast.nodes.InstanceOfExpression;
import org.phpaspect.weaver.ast.nodes.InterTypeClassConstantDeclaration;
import org.phpaspect.weaver.ast.nodes.InterTypeFieldsDeclaration;
import org.phpaspect.weaver.ast.nodes.InterfaceDeclaration;
import org.phpaspect.weaver.ast.nodes.ListVariable;
import org.phpaspect.weaver.ast.nodes.MethodDeclaration;
import org.phpaspect.weaver.ast.nodes.MethodInvocation;
import org.phpaspect.weaver.ast.nodes.ParenthesisExpression;
import org.phpaspect.weaver.ast.nodes.PostfixExpression;
import org.phpaspect.weaver.ast.nodes.PrefixExpression;
import org.phpaspect.weaver.ast.nodes.Program;
import org.phpaspect.weaver.ast.nodes.Quote;
import org.phpaspect.weaver.ast.nodes.Reference;
import org.phpaspect.weaver.ast.nodes.ReflectionVariable;
import org.phpaspect.weaver.ast.nodes.ReturnStatement;
import org.phpaspect.weaver.ast.nodes.Scalar;
import org.phpaspect.weaver.ast.nodes.SingleFieldDeclaration;
import org.phpaspect.weaver.ast.nodes.StaticConstantAccess;
import org.phpaspect.weaver.ast.nodes.StaticFieldAccess;
import org.phpaspect.weaver.ast.nodes.StaticMethodInvocation;
import org.phpaspect.weaver.ast.nodes.StaticStatement;
import org.phpaspect.weaver.ast.nodes.SwitchCase;
import org.phpaspect.weaver.ast.nodes.SwitchStatement;
import org.phpaspect.weaver.ast.nodes.ThrowStatement;
import org.phpaspect.weaver.ast.nodes.TryStatement;
import org.phpaspect.weaver.ast.nodes.UnaryOperation;
import org.phpaspect.weaver.ast.nodes.Variable;
import org.phpaspect.weaver.ast.nodes.WhileStatement;

public class AbstractVisitor implements Visitor {

	public void endVisit(ArrayAccess arrayAccess) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ArrayCreation arrayCreation) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ArrayElement arrayElement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Assignment assignment) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ASTError astError) {
		// TODO Auto-generated method stub

	}

	public void endVisit(BackTickExpression backTickExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Block block) {
		// TODO Auto-generated method stub

	}

	public void endVisit(BreakStatement breakStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(CastExpression castExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(CatchClause catchClause) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ClassConstantDeclaration classConstantDeclaration) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ClassDeclaration classDeclaration) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ClassInstanceCreation classInstanceCreation) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ClassName className) {
		// TODO Auto-generated method stub

	}

	public void endVisit(CloneExpression cloneExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Comment comment) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ConditionalExpression conditionalExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ContinueStatement continueStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(DeclareStatement declareStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(DoStatement doStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(EchoStatement echoStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(EmptyStatement emptyStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ExpressionStatement expressionStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(FieldAccess fieldAccess) {
		// TODO Auto-generated method stub

	}

	public void endVisit(FieldsDeclaration fieldsDeclaration) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ForEachStatement forEachStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(FormalParameter formalParameter) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ForStatement forStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(FunctionDeclaration functionDeclaration) {
		// TODO Auto-generated method stub

	}

	public void endVisit(FunctionInvocation functionInvocation) {
		// TODO Auto-generated method stub

	}

	public void endVisit(FunctionName functionName) {
		// TODO Auto-generated method stub

	}

	public void endVisit(GlobalStatement globalStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Identifier identifier) {
		// TODO Auto-generated method stub

	}

	public void endVisit(IfStatement ifStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(IgnoreError ignoreError) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Include include) {
		// TODO Auto-generated method stub

	}

	public void endVisit(InfixExpression infixExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(InLineHtml inLineHtml) {
		// TODO Auto-generated method stub

	}

	public void endVisit(InstanceOfExpression instanceOfExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(InterfaceDeclaration interfaceDeclaration) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ListVariable listVariable) {
		// TODO Auto-generated method stub

	}

	public void endVisit(MethodDeclaration methodDeclaration) {
		// TODO Auto-generated method stub

	}

	public void endVisit(MethodInvocation methodInvocation) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ParenthesisExpression parenthesisExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(PostfixExpression postfixExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(PrefixExpression prefixExpression) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Program program) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Quote quote) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Reference reference) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ReflectionVariable reflectionVariable) {
		// TODO Auto-generated method stub

	}

	public void endVisit(ReturnStatement returnStatement) {
		// TODO Auto-generated method stub

	}

	public void endVisit(Scalar scalar) {
		// TODO Auto-generated method stub

	}

	public void endVisit(SingleFieldDeclaration singleFieldDeclaration) {
		// TODO Auto-generated method stub

	}

	public void endVisit(StaticConstantAccess staticConstantAccess) {
		// TODO Auto-generated method stub

	}

	public void endVisit(StaticFieldAccess staticFieldAccess) {
		// TODO Auto-generated method stub

	}

	public void endVisit(StaticMethodInvocation staticMethodInvocation) {
		// TODO Auto-generated method stub

	}

	public void endVisit(StaticStatement staticStatement) {
		

	}

	public void endVisit(SwitchCase switchCase) {
		

	}

	public void endVisit(SwitchStatement switchStatement) {
		

	}

	public void endVisit(ThrowStatement throwStatement) {
		

	}

	public void endVisit(TryStatement tryStatement) {
		

	}

	public void endVisit(UnaryOperation unaryOperation) {
		

	}

	public void endVisit(Variable variable) {
		

	}

	public void endVisit(WhileStatement whileStatement) {
		

	}

	public void endVisit(ASTNode node) {
		

	}

	public void postVisit(ASTNode node) {
		

	}

	public void preVisit(ASTNode node) {
		

	}

	public boolean visit(ArrayAccess arrayAccess) {
		
		return true;
	}

	public boolean visit(ArrayCreation arrayCreation) {
		
		return true;
	}

	public boolean visit(ArrayElement arrayElement) {
		
		return true;
	}

	public boolean visit(Assignment assignment) {
		
		return true;
	}

	public boolean visit(ASTError astError) {
		
		return true;
	}

	public boolean visit(BackTickExpression backTickExpression) {
		
		return true;
	}

	public boolean visit(Block block) {
		
		return true;
	}

	public boolean visit(BreakStatement breakStatement) {
		
		return true;
	}

	public boolean visit(CastExpression castExpression) {
		
		return true;
	}

	public boolean visit(CatchClause catchClause) {
		
		return true;
	}

	public boolean visit(ClassConstantDeclaration classConstantDeclaration) {
		
		return true;
	}

	public boolean visit(ClassDeclaration classDeclaration) {
		
		return true;
	}

	public boolean visit(ClassInstanceCreation classInstanceCreation) {
		
		return true;
	}

	public boolean visit(ClassName className) {
		
		return true;
	}

	public boolean visit(CloneExpression cloneExpression) {
		
		return true;
	}

	public boolean visit(Comment comment) {
		
		return true;
	}

	public boolean visit(ConditionalExpression conditionalExpression) {
		
		return true;
	}

	public boolean visit(ContinueStatement continueStatement) {
		
		return true;
	}

	public boolean visit(DeclareStatement declareStatement) {
		
		return true;
	}

	public boolean visit(DoStatement doStatement) {
		
		return true;
	}

	public boolean visit(EchoStatement echoStatement) {
		
		return true;
	}

	public boolean visit(EmptyStatement emptyStatement) {
		
		return true;
	}

	public boolean visit(ExpressionStatement expressionStatement) {
		
		return true;
	}

	public boolean visit(FieldAccess fieldAccess) {
		
		return true;
	}

	public boolean visit(FieldsDeclaration fieldsDeclaration) {
		
		return true;
	}

	public boolean visit(ForEachStatement forEachStatement) {
		
		return true;
	}

	public boolean visit(FormalParameter formalParameter) {
		
		return true;
	}

	public boolean visit(ForStatement forStatement) {
		
		return true;
	}

	public boolean visit(FunctionDeclaration functionDeclaration) {
		
		return true;
	}

	public boolean visit(FunctionInvocation functionInvocation) {
		
		return true;
	}

	public boolean visit(FunctionName functionName) {
		
		return true;
	}

	public boolean visit(GlobalStatement globalStatement) {
		
		return true;
	}

	public boolean visit(Identifier identifier) {
		
		return true;
	}

	public boolean visit(IfStatement ifStatement) {
		
		return true;
	}

	public boolean visit(IgnoreError ignoreError) {
		
		return true;
	}

	public boolean visit(Include include) {
		
		return true;
	}

	public boolean visit(InfixExpression infixExpression) {
		
		return true;
	}

	public boolean visit(InLineHtml inLineHtml) {
		
		return true;
	}

	public boolean visit(InstanceOfExpression instanceOfExpression) {
		
		return true;
	}

	public boolean visit(InterfaceDeclaration interfaceDeclaration) {
		
		return true;
	}

	public boolean visit(ListVariable listVariable) {
		
		return true;
	}

	public boolean visit(MethodDeclaration methodDeclaration) {
		
		return true;
	}

	public boolean visit(MethodInvocation methodInvocation) {
		
		return true;
	}

	public boolean visit(ParenthesisExpression parenthesisExpression) {
		
		return true;
	}

	public boolean visit(PostfixExpression postfixExpression) {
		
		return true;
	}

	public boolean visit(PrefixExpression prefixExpression) {
		
		return true;
	}

	public boolean visit(Program program) {
		
		return true;
	}

	public boolean visit(Quote quote) {
		
		return true;
	}

	public boolean visit(Reference reference) {
		
		return true;
	}

	public boolean visit(ReflectionVariable reflectionVariable) {
		
		return true;
	}

	public boolean visit(ReturnStatement returnStatement) {
		
		return true;
	}

	public boolean visit(Scalar scalar) {
		
		return true;
	}

	public boolean visit(SingleFieldDeclaration singleFieldDeclaration) {
		
		return true;
	}

	public boolean visit(StaticConstantAccess classConstantAccess) {
		
		return true;
	}

	public boolean visit(StaticFieldAccess staticFieldAccess) {
		
		return true;
	}

	public boolean visit(StaticMethodInvocation staticMethodInvocation) {
		
		return true;
	}

	public boolean visit(StaticStatement staticStatement) {
		
		return true;
	}

	public boolean visit(SwitchCase switchCase) {
		
		return true;
	}

	public boolean visit(SwitchStatement switchStatement) {
		
		return true;
	}

	public boolean visit(ThrowStatement throwStatement) {
		
		return true;
	}

	public boolean visit(TryStatement tryStatement) {
		
		return true;
	}

	public boolean visit(UnaryOperation unaryOperation) {
		
		return true;
	}

	public boolean visit(Variable variable) {
		
		return true;
	}

	public boolean visit(WhileStatement whileStatement) {
		
		return true;
	}

	public boolean visit(ASTNode node) {
		
		return true;
	}

	public boolean endVisit(AspectDeclaration aspectDeclaration) {
		
		return true;
	}

	public boolean endVisit(
			InterTypeClassConstantDeclaration interTypeClassConstantDeclaration) {
		
		return true;
	}

	public boolean endVisit(
			InterTypeFieldsDeclaration interTypeFieldsDeclaration) {
		
		return true;
	}

	public boolean visit(AspectDeclaration aspectDeclaration) {
		
		return true;
	}

	public boolean visit(
			InterTypeClassConstantDeclaration interTypeClassConstantDeclaration) {
		
		return true;
	}

	public boolean visit(InterTypeFieldsDeclaration interTypeFieldsDeclaration) {
		
		return true;
	}

}
