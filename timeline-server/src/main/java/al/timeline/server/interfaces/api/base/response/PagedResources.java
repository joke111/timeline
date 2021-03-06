package al.timeline.server.interfaces.api.base.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.Collection;

public class PagedResources<T> {
	private PageMetadata metadata;
	private Collection<T> content;

	public PagedResources(Collection<T> content, PageMetadata metadata) {
		this.content = content;
		this.metadata = metadata;
	}

	public PagedResources(Page<T> page) {
		this.content = page.getContent();
		this.metadata = new PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
	}

	protected PagedResources() {

	}

	@JsonProperty("page")
	public PageMetadata getMetadata() {
		return metadata;
	}

	@JsonProperty("content")
	public Collection<T> getContent() {
		return content;
	}

	public Page<T> toPage(Pageable pageable) {
		return new PageImpl<>(Lists.newArrayList(getContent()), pageable, metadata.getTotalElements());
	}

	public static class PageMetadata {

		@JsonProperty private long size;
		@JsonProperty private long totalElements;
		@JsonProperty private long totalPages;
		@JsonProperty private long number;

		protected PageMetadata() {

		}

		/**
		 * Creates a new {@link PageMetadata} from the given size, number, total elements and total pages.
		 *
		 * @param size
		 * @param number zero-indexed, must be less than totalPages
		 * @param totalElements
		 * @param totalPages
		 */
		public PageMetadata(long size, long number, long totalElements, long totalPages) {

			Assert.isTrue(size > -1, "Size must not be negative!");
			Assert.isTrue(number > -1, "Number must not be negative!");
			Assert.isTrue(totalElements > -1, "Total elements must not be negative!");
			Assert.isTrue(totalPages > -1, "Total pages must not be negative!");

			this.size = size;
			this.number = number;
			this.totalElements = totalElements;
			this.totalPages = totalPages;
		}

		/**
		 * Creates a new {@link PageMetadata} from the given size, numer and total elements.
		 *
		 * @param size the size of the page
		 * @param number the number of the page
		 * @param totalElements the total number of elements available
		 */
		public PageMetadata(long size, long number, long totalElements) {
			this(size, number, totalElements, size == 0 ? 0 : (long) Math.ceil((double) totalElements / (double) size));
		}

		/**
		 * Returns the requested size of the page.
		 *
		 * @return the size a positive long.
		 */
		public long getSize() {
			return size;
		}

		/**
		 * Returns the total number of elements available.
		 *
		 * @return the totalElements a positive long.
		 */
		public long getTotalElements() {
			return totalElements;
		}

		/**
		 * Returns how many pages are available in total.
		 *
		 * @return the totalPages a positive long.
		 */
		public long getTotalPages() {
			return totalPages;
		}

		/**
		 * Returns the number of the current page.
		 *
		 * @return the number a positive long.
		 */
		public long getNumber() {
			return number;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return String.format("Metadata { number: %d, total pages: %d, total elements: %d, size: %d }", number,
				totalPages, totalElements, size);
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {

			if (this == obj) {
				return true;
			}

			if (obj == null || !obj.getClass().equals(getClass())) {
				return false;
			}

			PageMetadata that = (PageMetadata) obj;

			return this.number == that.number && this.size == that.size && this.totalElements == that.totalElements
				&& this.totalPages == that.totalPages;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {

			int result = 17;
			result += 31 * (int) (this.number ^ this.number >>> 32);
			result += 31 * (int) (this.size ^ this.size >>> 32);
			result += 31 * (int) (this.totalElements ^ this.totalElements >>> 32);
			result += 31 * (int) (this.totalPages ^ this.totalPages >>> 32);
			return result;
		}
	}
}
