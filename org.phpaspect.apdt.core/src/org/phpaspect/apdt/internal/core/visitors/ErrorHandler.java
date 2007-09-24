package org.phpaspect.apdt.internal.core.visitors;
import org.eclipse.php.internal.core.ast.nodes.ASTError;
import org.eclipse.php.internal.core.ast.nodes.ArrayAccess;
import org.eclipse.php.internal.core.ast.nodes.ArrayCreation;
import org.eclipse.php.internal.core.ast.nodes.ArrayElement;
import org.eclipse.php.internal.core.ast.nodes.Assignment;
import org.eclipse.php.internal.core.ast.nodes.BackTickExpression;
import org.eclipse.php.internal.core.ast.nodes.Block;
import org.eclipse.php.internal.core.ast.nodes.BreakStatement;
import org.eclipse.php.internal.core.ast.nodes.CastExpression;
import org.eclipse.php.internal.core.ast.nodes.CatchClause;
import org.eclipse.php.internal.core.ast.nodes.ClassConstantDeclaration;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.ClassInstanceCreation;
import org.eclipse.php.internal.core.ast.nodes.ClassName;
import org.eclipse.php.internal.core.ast.nodes.CloneExpression;
import org.eclipse.php.internal.core.ast.nodes.Comment;
import org.eclipse.php.internal.core.ast.nodes.ConditionalExpression;
import org.eclipse.php.internal.core.ast.nodes.ContinueStatement;
import org.eclipse.php.internal.core.ast.nodes.DeclareStatement;
import org.eclipse.php.internal.core.ast.nodes.DoStatement;
import org.eclipse.php.internal.core.ast.nodes.EchoStatement;
import org.eclipse.php.internal.core.ast.nodes.EmptyStatement;
import org.eclipse.php.internal.core.ast.nodes.ExpressionStatement;
import org.eclipse.php.internal.core.ast.nodes.FieldAccess;
import org.eclipse.php.internal.core.ast.nodes.FieldsDeclaration;
import org.eclipse.php.internal.core.ast.nodes.ForEachStatement;
import org.eclipse.php.internal.core.ast.nodes.ForStatement;
import org.eclipse.php.internal.core.ast.nodes.FormalParameter;
import org.eclipse.php.internal.core.ast.nodes.FunctionDeclaration;
import org.eclipse.php.internal.core.ast.nodes.FunctionInvocation;
import org.eclipse.php.internal.core.ast.nodes.FunctionName;
import org.eclipse.php.internal.core.ast.nodes.GlobalStatement;
import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.nodes.IfStatement;
import org.eclipse.php.internal.core.ast.nodes.IgnoreError;
import org.eclipse.php.internal.core.ast.nodes.InLineHtml;
import org.eclipse.php.internal.core.ast.nodes.Include;
import org.eclipse.php.internal.core.ast.nodes.InfixExpression;
import org.eclipse.php.internal.core.ast.nodes.InstanceOfExpression;
import org.eclipse.php.internal.core.ast.nodes.InterfaceDeclaration;
import org.eclipse.php.internal.core.ast.nodes.ListVariable;
import org.eclipse.php.internal.core.ast.nodes.MethodDeclaration;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.eclipse.php.internal.core.ast.nodes.ParenthesisExpression;
import org.eclipse.php.internal.core.ast.nodes.PostfixExpression;
import org.eclipse.php.internal.core.ast.nodes.PrefixExpression;
import org.eclipse.php.internal.core.ast.nodes.Program;
import org.eclipse.php.internal.core.ast.nodes.Quote;
import org.eclipse.php.internal.core.ast.nodes.Reference;
import org.eclipse.php.internal.core.ast.nodes.ReflectionVariable;
import org.eclipse.php.internal.core.ast.nodes.ReturnStatement;
import org.eclipse.php.internal.core.ast.nodes.Scalar;
import org.eclipse.php.internal.core.ast.nodes.StaticConstantAccess;
import org.eclipse.php.internal.core.ast.nodes.StaticFieldAccess;
import org.eclipse.php.internal.core.ast.nodes.StaticMethodInvocation;
import org.eclipse.php.internal.core.ast.nodes.StaticStatement;
import org.eclipse.php.internal.core.ast.nodes.SwitchCase;
import org.eclipse.php.internal.core.ast.nodes.SwitchStatement;
import org.eclipse.php.internal.core.ast.nodes.ThrowStatement;
import org.eclipse.php.internal.core.ast.nodes.TryStatement;
import org.eclipse.php.internal.core.ast.nodes.UnaryOperation;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.eclipse.php.internal.core.ast.nodes.WhileStatement;
import org.phpaspect.apdt.core.visitor.PHPAspectVisitor;
import org.phpaspect.apdt.internal.core.nodes.AspectDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeFieldDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeFieldsDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeMethodDeclaration;

public class ErrorHandler implements PHPAspectVisitor {

	public void visit(ArrayAccess arrayAccess) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ArrayCreation arrayCreation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ArrayElement arrayElement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Assignment assignment) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ASTError astError) {
		// TODO Auto-generated method stub
		
	}

	public void visit(BackTickExpression backTickExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Block block) {
		// TODO Auto-generated method stub
		
	}

	public void visit(BreakStatement breakStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(CastExpression castExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(CatchClause catchClause) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ClassConstantDeclaration classConstantDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ClassDeclaration classDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ClassInstanceCreation classInstanceCreation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ClassName className) {
		// TODO Auto-generated method stub
		
	}

	public void visit(CloneExpression cloneExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ConditionalExpression conditionalExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ContinueStatement continueStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(DeclareStatement declareStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(DoStatement doStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(EchoStatement echoStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(EmptyStatement emptyStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ExpressionStatement expressionStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FieldAccess fieldAccess) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FieldsDeclaration fieldsDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ForEachStatement forEachStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FormalParameter formalParameter) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ForStatement forStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FunctionDeclaration functionDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FunctionInvocation functionInvocation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FunctionName functionName) {
		// TODO Auto-generated method stub
		
	}

	public void visit(GlobalStatement globalStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Identifier identifier) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IgnoreError ignoreError) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Include include) {
		// TODO Auto-generated method stub
		
	}

	public void visit(InfixExpression infixExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(InLineHtml inLineHtml) {
		// TODO Auto-generated method stub
		
	}

	public void visit(InstanceOfExpression instanceOfExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(InterfaceDeclaration interfaceDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ListVariable listVariable) {
		// TODO Auto-generated method stub
		
	}

	public void visit(MethodDeclaration methodDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(MethodInvocation methodInvocation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ParenthesisExpression parenthesisExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(PostfixExpression postfixExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(PrefixExpression prefixExpression) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Program program) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Quote quote) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Reference reference) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ReflectionVariable reflectionVariable) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ReturnStatement returnStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Scalar scalar) {
		// TODO Auto-generated method stub
		
	}

	public void visit(StaticConstantAccess classConstantAccess) {
		// TODO Auto-generated method stub
		
	}

	public void visit(StaticFieldAccess staticFieldAccess) {
		// TODO Auto-generated method stub
		
	}

	public void visit(StaticMethodInvocation staticMethodInvocation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(StaticStatement staticStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(SwitchCase switchCase) {
		// TODO Auto-generated method stub
		
	}

	public void visit(SwitchStatement switchStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ThrowStatement throwStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(TryStatement tryStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(UnaryOperation unaryOperation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Variable variable) {
		// TODO Auto-generated method stub
		
	}

	public void visit(WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(AspectDeclaration aspectDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(AspectInterTypeDeclaration aspectInterTypeDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(
			AspectInterTypeFieldDeclaration aspectInterTypeFieldDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(
			AspectInterTypeFieldsDeclaration aspectInterTypeFieldsDeclaration) {
		// TODO Auto-generated method stub
		
	}

	public void visit(
			AspectInterTypeMethodDeclaration aspectInterTypeMethodDeclaration) {
		// TODO Auto-generated method stub
		
	}

}
