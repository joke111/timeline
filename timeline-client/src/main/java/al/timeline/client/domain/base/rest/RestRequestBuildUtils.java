package al.timeline.client.domain.base.rest;

import al.timeline.client.domain.base.exception.TimelineClientErrorCode;
import al.timeline.common.domain.base.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public final class RestRequestBuildUtils {

	private static final String CLASS_PROPERTY_NAME = "class";

	public static <T> String buildUrl(String baseUrl, String path, T t) {
		StringBuilder url = new StringBuilder().append(baseUrl).append(path);
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(t.getClass());
		if (propertyDescriptors != null) {
			List<PropertyDescriptor> parameters = Arrays.stream(propertyDescriptors)
				.filter(propertyDescriptor -> getPropertyValue(propertyDescriptor, t) != null)
				.filter(propertyDescriptor -> !CLASS_PROPERTY_NAME.equals(propertyDescriptor.getName()))
				.collect(Collectors.toList());
			parameters.stream().findFirst().ifPresent(propertyDescriptor -> url.append("?").append(buildParameter(t, propertyDescriptor)));
			parameters.stream().skip(1).forEach(propertyDescriptor -> url.append("&").append(buildParameter(t, propertyDescriptor)));
		}
		return url.toString();
	}

	public static <T> Map<String, Object> buildParameters(T t) {
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(t.getClass());
		if (propertyDescriptors != null) {
			return Arrays.stream(propertyDescriptors)
				.collect(Collectors.toMap(
					FeatureDescriptor::getName, propertyDescriptor -> Optional.ofNullable(getPropertyValue(propertyDescriptor, t))))
				.entrySet().stream()
				.filter(entry -> entry.getValue().isPresent())
				.filter(entry -> !CLASS_PROPERTY_NAME.equals(entry.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));
		}
		return new HashMap<>();
	}

	private static <T> Object getPropertyValue(PropertyDescriptor propertyDescriptor, T t) {
		try {
			return propertyDescriptor.getReadMethod().invoke(t);
		} catch (Exception e) {
			log.error("Fail to build request url", e);
			throw Exceptions.newException(TimelineClientErrorCode.BUILD_URL_FAILURE, e);
		}
	}

	private static <T> StringBuilder buildParameter(T t, PropertyDescriptor propertyDescriptor) {
		StringBuilder parameter = new StringBuilder();
		try {
			Optional.ofNullable(propertyDescriptor.getReadMethod().invoke(t))
				.ifPresent((value) -> {
					if (!CLASS_PROPERTY_NAME.equals(propertyDescriptor.getName())) {
						parameter.append(propertyDescriptor.getName())
							.append("=").append("{").append(propertyDescriptor.getName()).append("}");
					}
				});
			return parameter;
		} catch (Exception e) {
			log.error("Fail to build request url", e);
			throw Exceptions.newException(TimelineClientErrorCode.BUILD_URL_FAILURE, e);
		}
	}

}
