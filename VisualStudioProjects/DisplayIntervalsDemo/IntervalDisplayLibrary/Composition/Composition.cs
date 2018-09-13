using IntervalDisplayLibrary.Components;
using IntervalDisplayLibrary.Components.Interface;
using IntervalDisplayLibrary.Compositors;
using System;
using System.Drawing;

namespace IntervalDisplayLibrary.Composition // Move to components namespace?
{
    public class Composition : IComponent
    {
        public string Name { get; set; }

        private Composite _compositeRoot;
        private Compositor _compositor;

        public Composition(Compositor compositor)
        {
            if (compositor == null) throw new ArgumentNullException(nameof(compositor));
            _compositor = compositor;

            _compositeRoot = new Composite("ROOT");
        }

        public void Add(IComponent component)
        {
            _compositeRoot.Add(component);
        }

        public void Draw(Graphics graphics)
        {
            _compositor.Compose(_compositeRoot);

            _compositeRoot.Draw(graphics);
        }
    }
}