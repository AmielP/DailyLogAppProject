package ch.makery.log.services;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

public interface IFileOrDirectory 
{
	void setFileOrDirectory(Optional<File> fileOrDirectory);

	Optional<File> getFileOrDirectory();

	void setFolder(Path Folder);

	Path getFolder();
}
