package lba_backend.Services;

import java.util.List;

import lba_backend.Entities.Prebook;

public interface PrebookInterface
{
	public String        save(Prebook prebook);
	public List<Prebook> get(String username);
	public String        delete(String username,Long id);
}
