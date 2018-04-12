using Model.ValueTypes;
using System;

namespace CompositeClassLibrary.CompositePattern
{
    public class ComponentInterval : IntervalComponent, IComponent
    {
        public string Name { get; set; }

        public ComponentInterval(string name, IntervalType interval)
            : base(name, interval)
        {
            Name = name;
        }

        public void Add(IComponent component)
        {
            throw new NotImplementedException();
        }
    }
}