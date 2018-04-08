using System;
using System.Collections.Generic;
using Triangle.Interface;

namespace Triangle
{
    public abstract class Shape
    {
        public IReadOnlyList<Line> Lines { get; }
        private readonly IGeometryResolver _geometryResolver;

        protected Shape(IReadOnlyList<Line> line, IGeometryResolver geometryResolver)
        {
            if (line == null) throw new ArgumentNullException(nameof(line));
            if (geometryResolver == null) throw new ArgumentNullException(nameof(geometryResolver));

            Lines = line;
            _geometryResolver = geometryResolver;
        }

        public GeometryType GetGeometryType()
        {
            return _geometryResolver.GetGeometryType(Lines);
        }
    }
}