using System.Collections.Generic;

namespace Triangle.Interface
{
    public interface IGeometryResolver
    {
        GeometryType GetGeometryType(IReadOnlyList<Line> lines);
    }
}