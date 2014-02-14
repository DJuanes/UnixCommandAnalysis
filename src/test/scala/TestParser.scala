import org.scalatest._
import org.scalatest.matchers.Matchers

/**
 * TODO: Make these more robust
 */
class TestParser extends FlatSpec with Matchers {

  "A command" should "generate a parse tree" in {
    val driver = new CLIParserDriver
    val simplePipeline = "ls -la --color=true `du`| grep \"foo\";exit"
    val tree = driver.getSyntaxTree(simplePipeline)
    assert( tree != null)
    System.out.println(tree.toStringTree)
  }

  "A command" should "have command extracted from it" in {
    val driver = new CLIParserDriver
    val simplePipeline = "ls -la --color=true `du`| grep \"foo\";exit"
    val tree = driver.getSyntaxTree(simplePipeline)
    val commandList = driver.getCommandTokens(tree)
    System.out.println(commandList)
    assert(commandList != null)
  }

  "A command" should "have command pairs extracted from it" in {
    val driver = new CLIParserDriver
    val simplePipeline = "ls -la --color=true `du`| grep \"foo\";exit"
    val tree = driver.getSyntaxTree(simplePipeline)
    val commandList = driver.getCommandTokens(tree)
    val commandPairs = driver.toCommandPairs(commandList)
    System.out.println(commandPairs)
    assert(commandPairs != null)
    assert(commandPairs.contains(("ls", "du")))
    assert(commandPairs.contains(("ls", "grep")))
    assert(commandPairs.contains(("ls", "exit")))
    assert(commandPairs.contains(("grep", "exit")))
  }
}
