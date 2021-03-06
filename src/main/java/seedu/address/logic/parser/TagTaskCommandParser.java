package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.persons.TagCommand;
import seedu.address.logic.commands.tasks.TagTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

//@@author raisa2010
/**
 * Parses input arguments and creates a new TagCommand object
 */
public class TagTaskCommandParser implements Parser<TagTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TagTaskCommand
     * and returns a TagTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index[] parsedIndices;
        Set<Tag> tagList;

        try {
            parsedIndices =  ParserUtil.parseIndices(argMultimap.getPreamble().split(","));
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }
        return new TagTaskCommand(parsedIndices, tagList);
    }

}
