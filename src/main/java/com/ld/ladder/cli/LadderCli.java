package com.ld.ladder.cli;

import com.ld.ladder.logic.Translator;
import com.ld.ladder.parser.Lexer;
import com.ld.ladder.parser.Parser;
import com.ld.ladder.model.Diagram;
import com.ld.ladder.logic.Expr;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name="ladder", mixinStandardHelpOptions = true, description="Parse ladder diagram DSL into propositional logic")
public class LadderCli implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", arity = "0..1", description = "Input ladder file (.ld). If omitted, read stdin.")
    private Path file;

    @CommandLine.Option(names = "--format", defaultValue = "pretty", description = "Output format: pretty|ascii")
    private String format;

    public static void main(String[] args) {
        int code = new CommandLine(new LadderCli()).execute(args);
        System.exit(code);
    }

    @Override
    public Integer call() throws Exception {
        String source = file != null ? readFile(file) : readStdIn();
        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer.lex());
        Diagram diagram = parser.parseDiagram();
        Translator translator = new Translator();
        Map<String, Expr> map = translator.translate(diagram);
        boolean ascii = format.equalsIgnoreCase("ascii");
        map.forEach((coil, expr) -> {
            String rhs = ascii ? expr.toAsciiString() : expr.toPrettyString();
            System.out.println(coil + " := " + rhs);
        });
        return 0;
    }

    private String readFile(Path path) throws IOException { return Files.readString(path); }
    private String readStdIn() throws IOException { return new String(System.in.readAllBytes()); }
}
