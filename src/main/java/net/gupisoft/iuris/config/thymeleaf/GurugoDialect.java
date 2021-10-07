package net.gupisoft.iuris.config.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import net.gupisoft.iuris.config.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import net.gupisoft.iuris.config.thymeleaf.processor.MenuAttributeTagProcessor;
import net.gupisoft.iuris.config.thymeleaf.processor.MessageElementTagProcessor;

public class GurugoDialect extends AbstractProcessorDialect{

	public GurugoDialect() {
		super("GurugoNet", "gurugo", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
